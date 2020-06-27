package cn.cxd.services;

import cn.cxd.Impls.IAccountDao;
import cn.cxd.Impls.IAccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


public class AccountService02 implements IAccountService {
    private IAccountDao accountDao;
    @Override
    public void transferAccount(String sender, String receiver, double money) {
        accountDao.addAcount(receiver,money);
        int i = 10/0;
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
