package cn.cxd.springAopXmlConfig.service;

public class BookService  {
    private String name;

    public int addBook(String bookid) {
        System.out.println("BookService addBook 任务执行 : "+bookid);

        try {
            int i = 10/0;
        } catch (Exception e) {
            System.out.println("进入catch块");
        } finally {
            System.out.println("进入finally块");
        }

        return 12306;
    }


    public void delBook() {
        System.out.println("BookService delBook 任务执行");
    }
}
