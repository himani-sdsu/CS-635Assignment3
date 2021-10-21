package com.example.assignment2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//InventoryImpl --> Inventory.java
public class InventoryImpl implements Inventory {

	private ArrayList<Book> bookCollection = new ArrayList<Book>();
	private BookCollectionState bookCollectionMemento = new BookCollectionState();
	private InventoryDecorator invent;
	// not sure if this should be here used to getState makes me feel like this will
	// be the originator class
	private Integer numberOfState = 0;// constant file-- > CommandFileName, timeToSave = 3;

	public ArrayList<Book> getBookCollection() {
		return bookCollection;
	}

	public void setBookCollection(ArrayList<Book> bookCollection) {
		this.bookCollection = bookCollection;
	}

	@Override
	public void addBook(Book newBook) {
		// TODO Auto-generated method stub
		bookCollection.add(newBook);
		numberOfState++;
		if (numberOfState == Constants.TIME_TO_SAVE) {
			this.saveState();
			this.numberOfState = 0;
		}
	}

	@Override
	public void sellBook(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub
		for (Book book : bookCollection) {
			if ((book.getQuantity() > 0) && book.getBookName().equalsIgnoreCase(bookName)) {
				book.changeQuantityBy(-1);
				if (numberOfState == Constants.TIME_TO_SAVE) {// can this be put into a method??coz it's repetitve
					this.saveState();
					this.numberOfState = 0;
				}
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
//				if (numberOfState == Constants.TIME_TO_SAVE) {
//					this.saveState();
//					this.numberOfState = 0;
//				}
//				return;
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
				if (numberOfState == Constants.TIME_TO_SAVE) {
					this.saveState();
					this.numberOfState = 0;
				}
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

	@Override
	public void saveState() {
		// TODO Auto-generated method stub
//		bookCollectionMemento = new BookCollectionState(bookCollection);//creating new memento for inventory not in code but i think it should be here
		bookCollectionMemento.saveInventoryState(bookCollection);//saves memento
//yahaan pe it should save it to file i.e inventory file
		invent.saveState(); //--isse change krna hoga shayad
		File commandFile = new File(Constants.COMMAND_FILE_NAME);//deletes command file
		commandFile.delete();// deletes command file
		try {
			commandFile.createNewFile();//creates new command file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void restoreState() {
//		bookCollection.restore(bookCollectionMemento);//not possible since bookcollection not a separate class else would have done
	}
	@Override
	public void getState() {
		// TODO Auto-generated method stub
		invent.getState();
		bookCollection = invent.getInvent().getBookCollection();
	}

}
