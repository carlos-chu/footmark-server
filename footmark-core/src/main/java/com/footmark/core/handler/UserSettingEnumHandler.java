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

import com.footmark.core.enums.UserSettingEnum;

public class UserSettingEnumHandler extends BaseTypeHandler<UserSettingEnum> implements TypeHandler<UserSettingEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UserSettingEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getKey());
    }

    @Override
    public UserSettingEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return code == null ? null : UserSettingEnum.toEnum(code);
    }

    @Override
    public UserSettingEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return code == null ? null : UserSettingEnum.toEnum(code);
    }

    @Override
    public UserSettingEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return code == null ? null : UserSettingEnum.toEnum(code);
    }

}