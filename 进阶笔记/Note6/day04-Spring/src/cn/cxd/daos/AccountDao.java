package cn.cxd.daos;

import cn.cxd.Impls.IAccountDao;
import cn.cxd.beans.AccountUser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;
import java.util.Map;


public class AccountDao extends JdbcDaoSupport implements IAccountDao {

    @Override
    public void addAcount(String name, double money) {
        getJdbcTemplate().update("update account set money = money + ? where name = ?",money,name);
    }

    @Override
    public void subAcount(String name, double money) {
        getJdbcTemplate().update("update account set money = money - ? where name = ?",money,name);
    }

    @Override
    public double queryAccount(String name) {
        List<AccountUser> accountUsers = getJdbcTemplate().query("select money from account where name = ?", new BeanPropertyRowMapper<AccountUser>(AccountUser.class), name);
        return accountUsers.get(0).getMoney();
    }
}
