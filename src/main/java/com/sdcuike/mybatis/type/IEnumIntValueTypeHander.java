package com.sdcuike.mybatis.type;

import com.sdcuike.validation.IEnumIntValue;
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
public class IEnumIntValueTypeHander extends BaseTypeHandler<IEnumIntValue> {
    
    private Class<IEnumIntValue> type;
    
    @SuppressWarnings("unchecked")
    public IEnumIntValueTypeHander() {
        MappedTypes annotation = getClass().getAnnotation(MappedTypes.class);
        if (annotation == null) {
            throw new RuntimeException("typehandler:" + getClass().getName() + " MappedTypes annotation value is empty ");
        }
        
        type = (Class<IEnumIntValue>) annotation.value()[0];
        
    }
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IEnumIntValue parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
        
    }
    
    @Override
    public IEnumIntValue getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int index = rs.getInt(columnName);
        return IEnumIntValue.of(type, index);
    }
    
    @Override
    public IEnumIntValue getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int index = rs.getInt(columnIndex);
        return IEnumIntValue.of(type, index);
    }
    
    @Override
    public IEnumIntValue getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int index = cs.getInt(columnIndex);
        return IEnumIntValue.of(type, index);
    }
}
