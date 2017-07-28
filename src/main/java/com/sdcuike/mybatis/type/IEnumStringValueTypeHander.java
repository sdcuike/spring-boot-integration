package com.sdcuike.mybatis.type;

import com.sdcuike.validation.IEnumStringValue;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by beaver on 2017/6/9.
 */
public abstract class IEnumStringValueTypeHander extends BaseTypeHandler<IEnumStringValue> {
    
    private Class<IEnumStringValue> type;
    
    @SuppressWarnings("unchecked")
    public IEnumStringValueTypeHander() {
        MappedTypes annotation = getClass().getAnnotation(MappedTypes.class);
        if (annotation == null) {
            throw new RuntimeException("typehandler:" + getClass().getName() + " MappedTypes annotation value is empty ");
        }
        
        type = (Class<IEnumStringValue>) annotation.value()[0];
        
    }
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IEnumStringValue parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
        
    }
    
    @Override
    public IEnumStringValue getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return IEnumStringValue.of(type, value);
    }
    
    @Override
    public IEnumStringValue getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return IEnumStringValue.of(type, value);
    }
    
    @Override
    public IEnumStringValue getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return IEnumStringValue.of(type, value);
        
    }
}
