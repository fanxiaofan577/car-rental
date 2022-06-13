package cn.paopao.zuchebackend.Annotation.interceptor;


import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 时间拦截器.
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class CustomInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            //插入操作时，自动插入env
            Field fieldCreate = object.getClass().getDeclaredField("create_time");
            fieldCreate.setAccessible(true);
            fieldCreate.set(object, new Date());
        }else{
            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                //update时，自动更新updated_at
                Field fieldUpdate = object.getClass().getDeclaredField("updated_time");
                fieldUpdate.setAccessible(true);
                fieldUpdate.set(object, new Date());
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