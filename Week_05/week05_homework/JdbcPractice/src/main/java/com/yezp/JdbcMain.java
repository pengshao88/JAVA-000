package com.yezp;

import com.yezp.Dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        User stephen = new User(1, "stephen", 20);
        User tom = new User(2, "tom", 23);

        System.out.println("insert user name : stephen " + userDao.addUser(stephen));
        System.out.println("insert user name : tom " + userDao.addUser(tom));
        System.out.println("findUser by name stephen : " + userDao.findUserByName("stephen"));

        stephen.setAge(29);
        System.out.println("update stephen age to 29 : " + userDao.updateUser(stephen));

        System.out.println("delete tom : " + userDao.deleteUser(tom.getId()));
        System.out.println("findUser by name tom : " + userDao.findUserByName("tom"));

        JdbcManager jdbcManager = (JdbcManager) context.getBean("jdbcManager");
        jdbcManager.close();
    }

}
