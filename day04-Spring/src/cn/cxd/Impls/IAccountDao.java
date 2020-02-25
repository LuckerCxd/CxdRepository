package cn.cxd.Impls;

public interface IAccountDao {
    public void addAcount(String name,double money);
    public void subAcount(String name,double money);
    public double queryAccount(String name);
}
