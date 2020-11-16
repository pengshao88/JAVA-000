package com.yezp;

import com.yezp.Dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created on 2020/11/15 21:57.
 *
 * @author yezp
 */
public class JdbcMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");

        System.out.println("insert user name : wang " + userDao.addUser(3, "wang", 22));
        System.out.println("insert user name : li " + userDao.addUser(4, "li", 28));
        System.out.println("update stephen age to 30 : " + userDao.updateUser(1, "stephen", 30));
        System.out.println("delete wang : " + userDao.deleteUserBy(3));

        userDao.getUserList().forEach(user -> System.out.println(user));

        System.out.println("=======================================================");
        User stephen = new User(1, "stephen", 20);
        User tom = new User(2, "tom", 23);

        System.out.println("insert user name : stephen " + userDao.addUser(stephen));
        System.out.println("insert user name : tom " + userDao.addUser(tom));
        System.out.println("findUser by name stephen : " + userDao.findUserByName("stephen"));

        stephen.setAge(29);
        System.out.println("update stephen age to 29 : " + userDao.updateUser(stephen));

        System.out.println("delete tom : " + userDao.deleteUser(tom.getId()));
        System.out.println("findUser by name tom : " + userDao.findUserByName("tom"));
        userDao.getUserList().forEach(user -> System.out.println(user));

        System.out.println("==================用事务的方式提交批处理==================");
        List<User> userList = new ArrayList<>(2);
        userList.add(new User(5, "iu", 18));
        userList.add(new User(6, "rt", 20));
        System.out.println("使用事务的方式， 批处理：" + userDao.batchAddUser(userList));
        userDao.getUserList().forEach(user -> System.out.println(user));

        JdbcManager jdbcManager = (JdbcManager) context.getBean("jdbcManager");
        jdbcManager.close();
    }

}
