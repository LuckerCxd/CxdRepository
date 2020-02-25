package cn.cxd.services;

import cn.cxd.Impls.IAccountDao;
import cn.cxd.Impls.IAccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


public class AccountService01 implements IAccountService {
    private TransactionTemplate transactionTemplate;

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    private IAccountDao accountDao;
    @Override
    public void transferAccount(String sender, String receiver, double money) {
        this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                accountDao.addAcount(receiver,money);
                int i = 10/0;
                accountDao.subAcount(sender,money);
            }
        });

    }

    public IAccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public double checkAccount(String owner) {
       return this.transactionTemplate.execute(new TransactionCallback<Double>() {
            @Override
            public Double doInTransaction(TransactionStatus transactionStatus) {
                return accountDao.queryAccount(owner);
            }
        });
    }
}
