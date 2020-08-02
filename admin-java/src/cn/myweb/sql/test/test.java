package cn.myweb.sql.test;

import cn.myweb.sql.User;
import cn.myweb.sql.dao.UserDao;
import cn.myweb.sql.utils.JDBCUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

public class test {
    @Test
    public void testlogin(){
       User user=new User();
       user.setPassword("123456");
       user.setUsername("root");

       UserDao dao = new UserDao();
       System.out.println(dao.login(user));

    }

    @Test
    public void test1() throws IOException {
        String a= "{\"username\":\"sadsad\",\"password\":\"sadasd\"}";
        System.out.println(a);
        ObjectMapper b=new ObjectMapper();
        ObjectMapper aaa=new ObjectMapper();
        User user = b.readValue(a, User.class);
        System.out.println("sc"+user.toString());
    }
}
