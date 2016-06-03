/**
cwq
 */
package com.footmark.core.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.footmark.core.enums.UserStatusEnum;

public class UserStatusEnumHandler extends BaseTypeHandler<UserStatusEnum> implements TypeHandler<UserStatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UserStatusEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public UserStatusEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return code == null ? null : UserStatusEnum.toEnum(code);
    }

    @Override
    public UserStatusEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return code == null ? null : UserStatusEnum.toEnum(code);
    }

    @Override
    public UserStatusEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return code == null ? null : UserStatusEnum.toEnum(code);
    }

}