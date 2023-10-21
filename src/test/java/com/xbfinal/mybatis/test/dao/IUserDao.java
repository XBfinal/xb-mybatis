package com.xbfinal.mybatis.test.dao;

/**
 * @version 1.0
 * @Author 笑霸final
 * @Date 2023/10/21 12:57
 * @注释
 */
public interface IUserDao {
    String queryUserName(String uId);

    String queryUserAge(String uId);
}
