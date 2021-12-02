package com.kaixiang.module.db.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
@MappedTypes(value = UUID.class)
public class UuidTypeHandler implements TypeHandler<UUID> {

    // 此为给预编译sql赋值时当参数类型为自定义类型时, sql如何将value拼接到sql中
    @Override
    public void setParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public UUID getResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return UUID.fromString(rs.getString(columnName));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public UUID getResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return UUID.fromString(rs.getString(columnIndex));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public UUID getResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            return UUID.fromString(cs.getString(columnIndex));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
