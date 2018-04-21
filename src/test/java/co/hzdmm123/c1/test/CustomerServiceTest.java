package co.hzdmm123.c1.test;

import co.hzdmm123.c1.model.Customer;
import co.hzdmm123.c1.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hzdmm123 on 2018/4/21.
 */
public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest(){
        customerService = new CustomerService();
    }

    @Before
    public void init(){
        //初始化数据库
    }

    @Test
    public void getCustomerListTest(){
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2,customerList.size());

    }

    @Test
    public void getCustomerTest(){
        Customer customer = customerService.getCustomer(1);
        Assert.assertNotNull(customer);

    }


    @Test
    public void createCustomerTest() throws Exception{
        Map<String,Object> fileMap = new HashMap<String, Object>();
        fileMap.put("name","customer100");
        fileMap.put("contact","hzdmm123");
        fileMap.put("telephone","129");
        boolean res = customerService.createCustomer(fileMap);
        Assert.assertTrue(res);
    }

    @Test
    public void updateCustomerTest() throws Exception{
        Map<String,Object> fileMap = new HashMap<String, Object>();
        fileMap.put("contact","hzdmm123578");
        boolean res = customerService.updateCustomer(1,fileMap);
        Assert.assertTrue(res);
    }

    @Test
    public void deleteCustomerTest() throws Exception{
        long id = 1;
        boolean res = customerService.deleteCustomer(id);
        Assert.assertTrue(res);
    }


}
