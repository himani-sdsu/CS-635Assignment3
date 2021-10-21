package com.example.assignment2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class InventoryDecorator implements Inventory {// this is

	private InventoryImpl invent = new InventoryImpl();

	private CareTaker careTaker = new CareTaker();

	private BookCollectionState bookCollectionMemento = new BookCollectionState();
	private ArrayList<Command> commandList = new ArrayList<Command>();
	FileInputStream fileIn;

	public InventoryDecorator(InventoryImpl invent) {
		super();
		this.invent = invent;
	}

	public InventoryImpl getInvent() {
		return invent;
	}

	public void setInvent(InventoryImpl invent) {
		this.invent = invent;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		AddBookCommand addBook = new AddBookCommand(book);// create obj of cmnd type
		//save the command object here
		addBook.execute(invent);// adds book to this inventory, saves cmnd to file
	}

	@Override
	public void sellBook(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCopy(String bookName, int numberOfCopy) throws BookNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePrice(String bookName, float newPrice) throws BookNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public Float findPriceByName(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findQuantityByName(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findQuantityByID(int bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float findPriceByID(int bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveState() {//when does the code come here??
		// TODO Auto-generated method stub
		bookCollectionMemento.saveInventoryState(invent.getBookCollection());
		careTaker.serializeBookCollection(bookCollectionMemento);
		File commandFile = new File(Constants.COMMAND_FILE_NAME);
		commandFile.delete();// not sure if this should be here
		try {
			commandFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void getState() {// may be the name should be save
		// TODO Auto-generated method stub
		bookCollectionMemento = careTaker.deserializeBookCollection();
		invent.setBookCollection(bookCollectionMemento.getState());// set the memento from serialized file
		this.replayCommands(invent);// run all the commands
//		invent.s
//		invent.getState();
//		bookCollection = invent.getState();
	}

	public void replayCommands(InventoryImpl inventory) {
		try {
			fileIn = new FileInputStream(Constants.COMMAND_FILE_NAME);
			while (true) {
				ObjectInputStream input = new ObjectInputStream(fileIn);
				commandList.add((Command) input.readObject());
			}
		} catch (EOFException e) {
			try {
				fileIn.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException c) {
//			c.printStackTrace();
//		}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Command command : commandList) {
			command.execute(inventory);
		}
	}

//	@Override
//	public ArrayList<Book> getBookCollection() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
