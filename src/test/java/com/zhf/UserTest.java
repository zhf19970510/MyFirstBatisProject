package com.zhf;

import com.zhf.dao.UserMapper;
import com.zhf.pojo.User;
import com.zhf.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created on 2020/1/3 0003.
 */

public class UserTest {
    SqlSession sqlSession;

    //测试通过id得到用户信息
    @Test
    public void queryById() {
        sqlSession = MybatisUtil.getSqlSession();
        int id = 1;
        try {
            User user = sqlSession.selectOne("findUserById", id);
            sqlSession.commit();
            System.out.println(user.getId() + "," + user.getUsername() + "," + user.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSession(sqlSession);
        }
    }

    @Test
    public void findUserByName() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<User> users = sqlSession.selectList("findUserByName", "zhangsan");
        sqlSession.close();
        for (User user : users) {
            System.out.println(user.getId() + "," + user.getUsername() + "," + user.getAge());
        }
    }

    // 使用mapper代理去实现查询
    @Test
    public void testAddUser() throws Exception {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User("测试用户",20);
        userMapper.addUser(user);
        sqlSession.close();

    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        sqlSession.delete("deleteUser",3);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        User user = new User(3,"zhaoliu",20);
        sqlSession.update("updateUser",user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void inserUser(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        User user = new User("zhf",22);
        sqlSession.insert("addUser",user);
        sqlSession.close();
        System.out.println(user.getId());
    }


}
