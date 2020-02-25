package cn.cxd.Impls;

public interface IAccountService {
    public void transferAccount(String sender,String receiver,double money);
    public double checkAccount(String owner);
}
