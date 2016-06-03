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

import com.footmark.core.enums.RelationAcceptStatusEnum;

public class RelationAcceptStatusEnumHandler extends BaseTypeHandler<RelationAcceptStatusEnum> implements
        TypeHandler<RelationAcceptStatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, RelationAcceptStatusEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public RelationAcceptStatusEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return code == null ? null : RelationAcceptStatusEnum.toEnum(code);
    }

    @Override
    public RelationAcceptStatusEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return code == null ? null : RelationAcceptStatusEnum.toEnum(code);
    }

    @Override
    public RelationAcceptStatusEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return code == null ? null : RelationAcceptStatusEnum.toEnum(code);
    }

}