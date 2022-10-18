package miniproject.onlib.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "owned_date")
	private Date ownedDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getOwnedDate() {
		return ownedDate;
	}
	public void setOwnedDate(Date ownedDate) {
		this.ownedDate = ownedDate;
	}
	public Book(Long id, String name, String author, Double price, String publisher, int stock, boolean active,
			Date ownedDate) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
		this.stock = stock;
		this.active = active;
		this.ownedDate = ownedDate;
	}
	
	public Book() {
	}
	
}
