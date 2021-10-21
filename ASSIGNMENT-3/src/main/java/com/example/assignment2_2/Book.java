package com.example.assignment2_2;

import java.io.Serializable;

public class Book implements Serializable {
	private String bookName;
	private Float price;
	private Integer bookId;
	private Integer quantity;
//	private static int count = 0;// used to assign sequential numbers to bookId

	public Book(String bookName, Float price, int quantity) {
//		super();
//		this.bookId = this.bookId;
		this.bookName = bookName;
		this.price = price;
		this.quantity = quantity;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Float getPrice() {
		return price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	} //we don't need this i think

	public int getQuantity() {
		return quantity;
	}

	public void changeQuantityBy(int quantity) {
		this.quantity += quantity;
	}

	public void changePrice(float price) {
		this.price = price;
	}

	@Override
	public Object clone() {
		Book clone = new Book(bookName, price, quantity);
//		clone.count = count;
		return clone;
	}
}
