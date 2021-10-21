package com.example.assignment2_2;

import java.util.ArrayList;
import java.util.Iterator;

//InventoryImpl --> Inventory.java -- originator class
public class InventoryImpl implements Inventory {
	private static int count = 0;
	private ArrayList<Book> bookCollection = new ArrayList<Book>();

	public ArrayList<Book> getBookCollection() {
		return bookCollection;
	}

	public void setBookCollection(ArrayList<Book> bookCollection) {
		this.bookCollection = bookCollection;
	}

//	public static int getCount() {
//		return count;
//	}

	@Override
	public void addBook(Book newBook) {
		// TODO Auto-generated method stub
		newBook.setBookId(++count);
		bookCollection.add(newBook);
	}

	@Override
	public void sellBook(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub
		for (Book book : bookCollection) {
			if ((book.getQuantity() > 0) && book.getBookName().equalsIgnoreCase(bookName)) {
				book.changeQuantityBy(-1);
				return;
			}
		}
		throw new BookNotFoundException(bookName + " not found in the inventory");

	}

	@Override
	public void addCopy(String bookName, int numberOfCopy) throws BookNotFoundException {
		// TODO Auto-generated method stub
		for (Book book : bookCollection) {
			if ((book.getQuantity() > 0) && book.getBookName().equalsIgnoreCase(bookName)) {
				book.changeQuantityBy(1);
				return;
			}
		}
		throw new BookNotFoundException(bookName + " not found in the inventory");
	}

	@Override
	public void changePrice(String bookName, float newPrice) throws BookNotFoundException {
		// TODO Auto-generated method stub
		for (Book book : bookCollection) {
			if ((book.getQuantity() > 0) && book.getBookName().equalsIgnoreCase(bookName)) {
				book.changePrice(newPrice);
				return;
			}
		}
		throw new BookNotFoundException(bookName + " not found in the inventory");

	}

	@Override
	public Float findPriceByName(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub
		for (Book book : bookCollection) {
			if ((book.getQuantity() > 0) && book.getBookName().equalsIgnoreCase(bookName)) {
				return book.getPrice();
			}
		}
		throw new BookNotFoundException(bookName + " not found in the inventory");
	}

	@Override
	public Integer findQuantityByName(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub
		for (Book book : bookCollection) {
			if ((book.getQuantity() > 0) && book.getBookName().equalsIgnoreCase(bookName)) {
				return book.getQuantity();
			}
		}
		throw new BookNotFoundException(bookName + " not found in the inventory");
	}

	@Override
	public Integer findQuantityByID(int bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		for (Book book : bookCollection) {
			if ((book.getQuantity() > 0) && (book.getBookId() == bookId)) {
				return book.getQuantity();
			}
		}
		throw new BookNotFoundException("Book with ID: " + bookId + " not found in the inventory");
	}

	@Override
	public Float findPriceByID(int bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		for (Book book : bookCollection) {
			if ((book.getQuantity() > 0) && (book.getBookId() == bookId)) {
				return book.getPrice();
			}
		}
		throw new BookNotFoundException("Book with ID: " + bookId + " not found in the inventory");
	}

	public InventoryMemento saveInventoryState() {
		ArrayList<Book> mementoBookCollection = new ArrayList<>();
		Iterator<Book> bookIterator = this.bookCollection.iterator();//updatedBookCollection.iterator();
		while (bookIterator.hasNext()) {
			mementoBookCollection.add((Book) bookIterator.next().clone());
		}
		return new InventoryMemento(mementoBookCollection, count);
	}

	public void restoreState(InventoryMemento oldInventory) {
		this.bookCollection = oldInventory.getBookCollection();
		count = oldInventory.getMaxId();
	}
	
	//initializing the file..
	//when the project loads.. we see if we have memento file
		//yes -- run restoreState file
}
