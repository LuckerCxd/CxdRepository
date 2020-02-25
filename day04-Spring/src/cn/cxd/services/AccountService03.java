package cn.cxd.services;

import cn.cxd.Impls.IAccountDao;
import cn.cxd.Impls.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("service")
@Transactional
public class AccountService03 implements IAccountService {

    @Autowired
    private IAccountDao accountDao;
    @Override
    public void transferAccount(String sender, String receiver, double money) {
        accountDao.addAcount(receiver,money);
       // int i = 10/0;
        accountDao.subAcount(sender,money);

    }
    public IAccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public double checkAccount(String owner) {
        return accountDao.queryAccount(owner);
    }
}
