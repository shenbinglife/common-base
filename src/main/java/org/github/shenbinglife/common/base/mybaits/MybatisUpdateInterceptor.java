package org.github.shenbinglife.common.base.mybaits;

import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import org.github.shenbinglife.common.base.entity.BaseEntity;
import org.github.shenbinglife.common.base.util.UUIDGenerator;

/**
 * Mybatis拦截器， 实现自动设置ID和createTime、updateTime
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisUpdateInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement stmt = (MappedStatement) invocation.getArgs()[0];
        Object param = invocation.getArgs()[1];
        if (stmt == null) {
            return invocation.proceed();
        }

        if (stmt.getSqlCommandType().equals(SqlCommandType.INSERT)) {
            if (param != null && param instanceof BaseEntity) {
                BaseEntity e = (BaseEntity) param;
                e.setCreateTime(new Date());
                e.setUpdateTime(new Date());
                if (StringUtils.isBlank(e.getId())) {
                    e.setId(UUIDGenerator.generate());
                }
            }
        }

        if (stmt.getSqlCommandType().equals(SqlCommandType.UPDATE)) {
            if (param != null && param instanceof BaseEntity) {
                BaseEntity e = (BaseEntity) param;
                e.setUpdateTime(new Date());
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
