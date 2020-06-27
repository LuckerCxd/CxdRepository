package cn.cxd.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 19:46 2020/5/14
 * @Modified By:
 */
public class BookBaseService {
    @Autowired
    private JdbcTemplate jdbcTemplate = null;


    public int findPrice(String ibsn){
        return jdbcTemplate.queryForObject("select price from book where ibsn = ?",int.class,ibsn);
    }

    public void updateStore(String ibsn){
        Integer store = jdbcTemplate.queryForObject("select store from bookstore where ibsn = ?", int.class, ibsn);
        if(store <= 0)
            throw new RuntimeException("书本库存不足!");
        jdbcTemplate.update("update bookstore set store = store-1 where ibsn = ?",ibsn);
    }

    public void updateAccount(String uname,int price){
        Integer money = jdbcTemplate.queryForObject("select money from account where name = ?", int.class, uname);
        if(money < price)
            throw new RuntimeException("金额不足！");
        jdbcTemplate.update("update account set money = money-? where name = ?",price,uname);
    }
}
