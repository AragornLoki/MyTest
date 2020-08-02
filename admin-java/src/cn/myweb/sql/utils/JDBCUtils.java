package cn.myweb.sql.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;

    public static DataSource getDs() {
        return ds;
    }

    public static Connection getConnection() {
        return null;
    }
    static {
        System.out.println(1);

        try {
            Properties pro = new Properties();
            InputStream in =  JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(in);
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
