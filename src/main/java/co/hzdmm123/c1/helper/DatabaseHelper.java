package co.hzdmm123.c1.helper;

import co.hzdmm123.c1.util.PropsUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by hzdmm123 on 2018/4/21.
 */
public class DatabaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    //db的runnner
    private static final  QueryRunner QUERY_RUNNER = new QueryRunner();
    //connection
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>();


    private static final  String DRIVER;
    private static final  String URL;
    private static final  String USERNAME;
    private static final  String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        try{
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            LOGGER.error("can not load jdbc driver",e);
        }
    }

    //读取数据库连接
    public static Connection getConnection(){
   /*     Connection conn = null;

        try{
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        }catch (SQLException e){
            LOGGER.error("get connection failure",e);
        }
        return conn;*/
        Connection conn  = CONNECTION_THREAD_LOCAL.get();
        if (conn == null){
            try{
                conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            }catch (SQLException e){
                LOGGER.error("get connection failure",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_THREAD_LOCAL.set(conn);
            }
        }
        return conn;
    }

    public static void closeConnection(){
        Connection conn = CONNECTION_THREAD_LOCAL.get();
        if (conn != null){
            try{
                conn.close();
            }catch (Exception e){
                LOGGER.error("close connection failure",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_THREAD_LOCAL.remove();
            }
        }
    }
    //查询实体列表
    public static <T> List<T> queryEntityList( Class<T> entityClass,String sql,Object ...params){
        List<T> entityList;
        try {
            Connection conn = getConnection();
            entityList = QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass),params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure",e);
            throw  new RuntimeException();
        } finally {
            closeConnection();
        }

        return entityList;

    }
    //查询单个实体
    public static <T> T queryEntity(Class<T> entityClass,String sql,Object ...params){
        T entity;
        try{
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn,sql,new BeanHandler<T>(entityClass));
        }catch (SQLException e){
            LOGGER.error("query entity failure",e);
            throw  new RuntimeException();
        }finally {
            closeConnection();
        }
        return entity;
    }
}
