package com.example.assignment2;

import java.util.ArrayList;

public interface Inventory {
	public void addBook(Book book);
	public void sellBook(String bookName)throws BookNotFoundException;
	public void addCopy(String bookName, int numberOfCopy ) throws BookNotFoundException;
	public void changePrice(String bookName,float newPrice) throws BookNotFoundException;
	public Float findPriceByName(String bookName) throws BookNotFoundException;
	public Integer findQuantityByName(String bookName) throws BookNotFoundException;
	public Integer findQuantityByID(int bookId) throws BookNotFoundException;
	public Float findPriceByID(int bookId) throws BookNotFoundException;
	public void saveState();
	public void getState();
//	public ArrayList<Book> getBookCollection();
}