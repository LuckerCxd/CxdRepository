package cn.cxd.springAopXmlConfig.service;

public class BookService  {
    private String name;

    public int addBook(String bookid) {
        System.out.println("BookService addBook 任务执行 : "+bookid);


        return 12306;
    }


    public void delBook() {
        System.out.println("BookService delBook 任务执行");
    }
}
