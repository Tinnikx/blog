package com.kaixiang.module.user.controller;

import static com.kaixiang.module.common.EventType.DELETE_ACCOUNT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaixiang.module.common.encoder.BlogAesPasswordEncoder;
import com.kaixiang.module.common.exception.BadRequestException;
import com.kaixiang.module.common.exception.ConflictException;
import com.kaixiang.module.common.exception.RecordNotFoundException;
import com.kaixiang.module.user.auth.IdentityProviderLookupService;
import com.kaixiang.module.user.dto.StandardUserRegisterDto;
import com.kaixiang.module.user.dto.UpgradePermissionDto;
import com.kaixiang.module.user.model.DelAccountRequestModel;
import com.kaixiang.module.user.model.RequestPermissionModel;
import com.kaixiang.module.user.service.RequestPermissionService;
import com.kaixiang.module.user.service.UserService;
import com.kaixiang.security.auth.dto.AuthenticatedUserDto;
import com.kaixiang.security.auth.provider.UserIdentityProvider;
import com.kaixiang.security.utils.AuthUtils;
import com.kaixiang.security.utils.BlogUrlGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;

@RestController
public class StandardUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandardUserController.class);

    @Value("${blog.delete.account.key}")
    private String delAccountKey;

    @Autowired
    private IdentityProviderLookupService identityProviderLookupService;

    @Autowired
    private RequestPermissionService requestPermissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogUrlGenerator generator;

    @Autowired
    private BlogAesPasswordEncoder encoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody @Validated StandardUserRegisterDto registerDto) throws BadRequestException, ConflictException {
        UserIdentityProvider identityProvider = identityProviderLookupService.lookup(registerDto.getSource());
        identityProvider.register(identityProvider.getConverter().convertToRegisterModel(registerDto));
    }

    @PutMapping("/upgrade")
    @PreAuthorize("hasAnyRole('STANDARD')")
    @Transactional
    public void upgrade(@RequestBody UpgradePermissionDto upgradePermissionDto) {
        //TODO maybe need message queue
        RequestPermissionModel requestPermissionModel = new RequestPermissionModel();
        requestPermissionModel.setReason(upgradePermissionDto.getReason());
        requestPermissionModel.setUserUuid(upgradePermissionDto.getUuid());
        requestPermissionService.createRequestPermissionRecord(requestPermissionModel);
    }

    @DeleteMapping("/del-account")
    @PreAuthorize("hasAnyRole('STANDARD', 'VIP', 'SUPER', 'ADMIN')")
    public ResponseEntity<?> requestDelAccount() throws RecordNotFoundException {
        //TODO send email to verify and store the info to db and delete the user while the email verified
        AuthenticatedUserDto currentUser = AuthUtils.getCurrentUser();
        DelAccountRequestModel requestModel = new DelAccountRequestModel();
        requestModel.setUuid(currentUser.getUuid());
        requestModel.setEmail(currentUser.getUsername());
        requestModel.setCreatedAt(LocalDateTime.now());
        requestModel.setEffectiveTime(Duration.ofDays(1));

        LinkedMultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("event", DELETE_ACCOUNT.name());
        try {
            params.add("params",
                URLEncoder.encode(encoder.encrypt(objectMapper.writeValueAsString(requestModel), delAccountKey), StandardCharsets.UTF_8));
        } catch (GeneralSecurityException | JsonProcessingException ignored) {
        }
        String delAccountUrl = generator.generate("http://localhost:8080/del-account/confirmed", params);
        HashMap<String, String> map = new HashMap<>();
        map.put("url", delAccountUrl);
        map.put("method", DELETE.name());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping(value = "/del-account/confirmed", params = "event=DELETE_ACCOUNT")
    @Transactional
    public ResponseEntity<?> delAccount(String params) {
        try {
            String decrypted = encoder.decrypt(params, delAccountKey);
            DelAccountRequestModel requestModel = objectMapper.readValue(decrypted, DelAccountRequestModel.class);
            if (!LocalDateTime.now().isAfter(requestModel.getCreatedAt().plus(requestModel.getEffectiveTime()))) {
                LOGGER.info("starting delete account for user which uuid is {}, email is {}",
                    requestModel.getUuid(), requestModel.getEmail());
                userService.delAccount(requestModel.getUuid());
                return new ResponseEntity<>(Collections.singletonMap("message", "delete successful"), HttpStatus.OK);
            }
            return new ResponseEntity<>(Collections.singletonMap("message", "timeout"), HttpStatus.NOT_ACCEPTABLE);
        } catch (GeneralSecurityException | JsonProcessingException e) {
            return new ResponseEntity<>(Collections.singletonMap("message", "params is illegal"), HttpStatus.BAD_REQUEST);
        }
    }
}
