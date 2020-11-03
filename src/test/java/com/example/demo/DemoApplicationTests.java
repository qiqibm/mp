package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectAll() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(22);
        user.setMail("adfad@qq.com");
        user.setName("tom1");
        user.setUserName("tom2");
        user.setPassword("111");
        user.setAddress("wuhan");

        int i = this.userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void testPage() {
        //select * from tb_user where limit 0,5
        //select count(*) from tb_user
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.ge("age", 10);

        Page<User> objectPage = new Page<>(1, 2);//  1 ，每页显示二条
        IPage<User> userIPage = this.userMapper.selectPage(objectPage, objectQueryWrapper);

        System.out.println(userIPage.getTotal());//总条数
        System.out.println(userIPage.getPages());//总页数
        List<User> records = userIPage.getRecords();//查询的数据
        for (User u : records) {
            System.out.println(u);
        }
    }

    @Test
    public void testLike(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","张");
        //queryWrapper.orderByAsc("age");
       // queryWrapper.select("id","age");
        List<User> users = this.userMapper.selectList(queryWrapper);
        for(User u:users){

            System.out.println(u);
        }


    }

}
