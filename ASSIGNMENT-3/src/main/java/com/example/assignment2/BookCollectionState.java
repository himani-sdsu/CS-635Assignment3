package com.example.assignment2;

import java.util.ArrayList;
//i have changed methods a bit from what we r following --> Memento.java
public class BookCollectionState {
	private ArrayList<Book> bookCollection;
	private InventoryImpl inventory;
	public BookCollectionState() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookCollectionState(ArrayList<Book> bookCollection) { //used to set state by the caretaker
//		super();
		this.bookCollection = bookCollection;
	}

//	// see for if this can be made list or should be arrayList
//	public ArrayList<Book> getInventoryState() {//used to get state by the caretaker
//		return this.bookCollection;
//	}
	
	//methods that will support do, undo operation for originator InventoryImpl
	public void saveInventoryState(ArrayList<Book> updatedBookCollection) {
		this.bookCollection = updatedBookCollection;
	}
	
//	public void restoreState() {
//		inventory.
//	}
	public ArrayList<Book> getState() {
		return this.bookCollection;
	}
	
	
}
