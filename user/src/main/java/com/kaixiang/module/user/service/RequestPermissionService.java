package com.kaixiang.module.user.service;

import com.kaixiang.module.user.entity.RequestPermission;
import com.kaixiang.module.user.model.RequestPermissionModel;
import com.kaixiang.module.user.repository.RequestPermissionMapper;
import com.kaixiang.module.user.upgrade.UpgradeProcessStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/2
 */
@Service
public class RequestPermissionService {

    @Autowired
    private RequestPermissionMapper requestPermissionMapper;

    @Transactional
    public void createRequestPermissionRecord(RequestPermissionModel model) {
        RequestPermission requestPermission = new RequestPermission();
        requestPermission.setUserUuid(model.getUserUuid());
        requestPermission.setReason(model.getReason());
        requestPermission.setStatus(UpgradeProcessStatus.Pending);
        requestPermissionMapper.create(requestPermission);
    }

    @Transactional
    public void deleteAllByUserUuid(UUID userUuid) {
        requestPermissionMapper.deleteAllByUserUuid(userUuid);
    }
}
