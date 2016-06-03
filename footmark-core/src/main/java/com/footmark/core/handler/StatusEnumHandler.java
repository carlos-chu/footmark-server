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

import com.footmark.common.enums.StatusEnum;

public class StatusEnumHandler extends BaseTypeHandler<StatusEnum> implements TypeHandler<StatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, StatusEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public StatusEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return code == null ? null : StatusEnum.toEnum(code);
    }

    @Override
    public StatusEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return code == null ? null : StatusEnum.toEnum(code);
    }

    @Override
    public StatusEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return code == null ? null : StatusEnum.toEnum(code);
    }

}