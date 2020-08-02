package cn.myweb.sql.dao;
import cn.myweb.sql.User;
import cn.myweb.sql.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());
    /**
     *
     * @param loginUser 用户名与密码
     * @return 包含所有数据
     */
    public User login(User loginUser){
        try {
            String sql="select * from user where username=? and password=?";
            return template.queryForObject(sql,
                     new BeanPropertyRowMapper<>(User.class),
                     loginUser.getUsername(),loginUser.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return  null;
        }

    }
}
