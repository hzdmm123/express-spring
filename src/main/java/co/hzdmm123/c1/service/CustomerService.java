package co.hzdmm123.c1.service;

import co.hzdmm123.c1.helper.DatabaseHelper;
import co.hzdmm123.c1.model.Customer;
import co.hzdmm123.c1.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by hzdmm123 on 2018/4/21.
 */
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    //获取客户列表
    public List<Customer> getCustomerList() {
   /*     Connection conn = null;
        List<Customer>  customerList = new ArrayList<Customer>();
        try{

            String sql = "SELECT * FROM customer";
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }
        }catch (SQLException e){
            LOGGER.error("execute sql failure",e);
        }finally {
            if (conn != null){
                try {
                    conn.close();
                }catch (SQLException e){
                    LOGGER.error("close connection failure",e);
                }
            }
        }

        return customerList;*/

/*
        Connection connection = DatabaseHelper.getConnection();
        try {
            String sql = "SELECT * FROM customer";
            return DatabaseHelper.queryEntityList(Customer.class,sql);
        }finally {
            DatabaseHelper.closeConnection();
        }
*/
        String sql = "select * from customer";
        return DatabaseHelper.queryEntityList(Customer.class,sql);

    }

    //获取客户
    public Customer getCustomer(long id) {
        return null;
    }

    //创建客户
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return false;
    }

    //变更客户
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return false;
    }

    //删除用户
    public boolean deleteCustomer(long id) {
        return false;
    }
}
