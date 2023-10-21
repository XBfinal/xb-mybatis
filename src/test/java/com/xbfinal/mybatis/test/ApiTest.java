package com.xbfinal.mybatis.test;


import com.xbfinal.mybatis.binding.MapperProxyFactory;
import com.xbfinal.mybatis.test.dao.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;



/**
 * @version 1.0
 * @Author 笑霸final
 * @Date 2023/10/21 12:56
 * @注释 测试类
 */
public class ApiTest {
    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();


        sqlSession.put("com.xbfinal.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("com.xbfinal.mybatis.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");

        IUserDao userDao = factory.newInstance(sqlSession);

        String res = userDao.queryUserName("10000");
        logger.info("测试结果：{}", res);
        String integer = userDao.queryUserAge("10001");
       logger.info("测试结果：{}", integer);
    }
}
