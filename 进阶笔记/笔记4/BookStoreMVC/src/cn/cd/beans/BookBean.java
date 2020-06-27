package cn.cd.beans;

public class BookBean {
	private String bookid = null;
	private String title = null;
	private String author = null;
	private String publisher = null;
	private float price = 0.0F;
	public BookBean() {	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public BookBean(String bookid, String title, String author, String publisher, float price) {
		this.bookid = bookid;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}
	
}
