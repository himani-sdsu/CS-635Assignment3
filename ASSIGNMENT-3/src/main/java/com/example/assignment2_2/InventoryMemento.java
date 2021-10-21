package com.example.assignment2_2;

import java.io.Serializable;
import java.util.ArrayList;
//memento
@SuppressWarnings("serial")
public class InventoryMemento implements Serializable {
	private ArrayList<Book> bookCollection;// = new ArrayList<Book>();
	private int maxId;

	public InventoryMemento(ArrayList<Book> bookCollection, int maxId) {
		super();
		this.bookCollection = bookCollection;
		this.maxId = maxId;
	}

	public ArrayList<Book> getBookCollection() {
		return bookCollection;
	}

	public int getMaxId() {
		return maxId;
	}

	// need to understand what we'll do to count variable in BookCollection
	// methods that will support do, undo operation for originator InventoryImpl
//	public void saveInventoryState(ArrayList<Book> updatedBookCollection, int count) {
//		bookCollection = new ArrayList<>();
//		Iterator<Book> bookIterator = updatedBookCollection.iterator();
//		while (bookIterator.hasNext()) {
//			Book updatedBook = (Book) bookIterator.next();
//			bookCollection.add((Book) updatedBook.clone());
////			maxId = 
//		}
//		this.bookCollection = updatedBookCollection;
//		this.maxId = count;
//	}
//
////	public void restoreState() {
////		inventory.
////	}
//	public ArrayList<Book> getState() {
//		return this.bookCollection;
//	}

}
