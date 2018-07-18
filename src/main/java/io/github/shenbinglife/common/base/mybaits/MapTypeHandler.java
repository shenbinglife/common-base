package io.github.shenbinglife.common.base.mybaits;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import io.github.shenbinglife.common.base.exception.UncheckedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 转换拓展字段（Map类型）为 JSON字符串存储
 *
 * @author shenbing
 * @version 2017/11/10
 * @since since
 */
@MappedTypes({Map.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class MapTypeHandler extends BaseTypeHandler<Map<String, Object>> {

    private final ObjectMapper mapper = new ObjectMapper();


    private Map<String, Object> getMap(String columnValue) {
        if (StringUtils.isBlank(columnValue)) {
            return null;
        }
        JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, Object.class);
        try {
            return this.mapper.readValue(columnValue, javaType);
        } catch (IOException e) {
            throw new UncheckedException("无法解析的json字符串");
        }
    }

    private String getColumnValue(Map<String, Object> map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new UncheckedException("无法将Map转为json字符串");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> parameter, JdbcType jdbcType)
                    throws SQLException {
        ps.setString(i, this.getColumnValue(parameter));
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.getMap(rs.getString(columnName));
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.getMap(rs.getString(columnIndex));
    }

    @Override
    public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.getMap(cs.getString(columnIndex));
    }
}
