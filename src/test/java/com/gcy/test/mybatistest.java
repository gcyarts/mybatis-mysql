package com.gcy.test;

import com.gcy.dao.IUserDao;
import com.gcy.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class mybatistest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    @Before
    public void init() throws Exception{
         in = Resources.getResourceAsStream("SqlMapConfig.xml");
         SqlSessionFactory  factory = new SqlSessionFactoryBuilder().build(in);
         sqlSession = factory.openSession();
         userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void destroy() throws Exception{
        sqlSession.close();
        in.close();
    }
    @Test
    public void testfindAll() {
        List<User> users = userDao.findAll();
        for(User user : users)
        {
            System.out.println(user);
        }

    }
    @Test
    public void testsave(){
        User user=new User();
        user.setAddress("鹤岗市");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setUsername("kss");
        userDao.saveUser(user);
        sqlSession.commit();
    }
    @Test
    public void testupdate(){
        String time = "2019-07-23";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user=new User();
        user.setId(2);
        user.setAddress("齐齐哈尔市");
        user.setBirthday(dateTime);
        user.setSex("女");
        user.setUsername("girl");
        userDao.updateUser(user);
        sqlSession.commit();
    }
    @Test
    public void deletetest(){
        userDao.deleteUser(7);
    }
}
