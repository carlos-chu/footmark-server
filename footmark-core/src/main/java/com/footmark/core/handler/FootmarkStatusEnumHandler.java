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

import com.footmark.core.enums.FootmarkStatusEnum;

public class FootmarkStatusEnumHandler extends BaseTypeHandler<FootmarkStatusEnum> implements
        TypeHandler<FootmarkStatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FootmarkStatusEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public FootmarkStatusEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return code == null ? null : FootmarkStatusEnum.toEnum(code);
    }

    @Override
    public FootmarkStatusEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return code == null ? null : FootmarkStatusEnum.toEnum(code);
    }

    @Override
    public FootmarkStatusEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return code == null ? null : FootmarkStatusEnum.toEnum(code);
    }

}