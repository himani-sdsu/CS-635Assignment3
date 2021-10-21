package com.example.assignment2_2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class InventoryDecorator implements Inventory {// this is caretaker

	private InventoryImpl invent;
	private Integer numberOfState = 0;
	private InventoryMemento inventoryMemento;
	private ArrayList<Command> commandList = new ArrayList<Command>();
	FileInputStream fileIn;

	public InventoryDecorator(InventoryImpl invent) {
		super();
		this.getState();
		this.invent = invent;
	}

	public InventoryImpl getInvent() {
		return invent;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		Command addBookCommand = new AddBookCommand(book);// create obj of cmnd type
		addBookCommand.execute(invent);// adds book to this inventory, saves cmnd to file
		this.writeCommandToFile(addBookCommand);
		numberOfState++;
		if (numberOfState == Constants.TIME_TO_SAVE) {
			this.saveMementoState();
		}
	}

	@Override
	public void sellBook(String bookName) {
		// TODO Auto-generated method stub
		SellBookCommand sellCommand = new SellBookCommand(bookName);
		sellCommand.execute(invent);
		this.writeCommandToFile(sellCommand);
		numberOfState++;
		if (numberOfState == Constants.TIME_TO_SAVE) {
			this.saveMementoState();
		}
	}

	@Override
	public void addCopy(String bookName, int numberOfCopy) {
		// TODO Auto-generated method stub
		AddBookCopyCommand addCopyCommand = new AddBookCopyCommand(bookName, numberOfCopy);
		addCopyCommand.execute(invent);
		this.writeCommandToFile(addCopyCommand);
		numberOfState++;
		if (numberOfState == Constants.TIME_TO_SAVE) {
			this.saveMementoState();
//			this.numberOfState = 0;
		}
	}

	@Override
	public void changePrice(String bookName, float newPrice) {
		// TODO Auto-generated method stub
		ChangePriceCommand changeBookPriceCommand = new ChangePriceCommand(bookName, newPrice);
		changeBookPriceCommand.execute(invent);
		numberOfState++;
		this.writeCommandToFile(changeBookPriceCommand);
		if (numberOfState == Constants.TIME_TO_SAVE) {
			this.saveMementoState();
		}
	}

	@Override
	public Float findPriceByName(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return invent.findPriceByName(bookName);
	}

	@Override
	public Integer findQuantityByName(String bookName) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return invent.findQuantityByName(bookName);
	}

	@Override
	public Integer findQuantityByID(int bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return invent.findQuantityByID(bookId);
	}

	@Override
	public Float findPriceByID(int bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return invent.findPriceByID(bookId);
	}

	public void writeCommandToFile(Command commandObject) {
		FileOutputStream fileStream;
		try {
			fileStream = new FileOutputStream(Constants.COMMAND_FILE_NAME, true);
			ObjectOutputStream objStream = new ObjectOutputStream(fileStream);
			objStream.writeObject(commandObject);
			objStream.close();
			fileStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveMementoState() {// when does the code come here??
		// TODO Auto-generated method stub
		InventoryMemento saveInventoryState = invent.saveInventoryState();// origin can store it's data coz
		SerializationUtil.serializeBookCollection(inventoryMemento);
		File commandFile = new File(Constants.COMMAND_FILE_NAME);
		commandFile.delete();// not sure if this should be here
		try {
			commandFile.createNewFile();
			this.numberOfState = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inventoryMemento = saveInventoryState;
	}

	// ask abt this.. where should we have this?? this will be called when system
	// re-starts after
	public void getState() {
		// TODO Auto-generated method stub
		inventoryMemento = SerializationUtil.deserializeBookCollection();
		invent.setBookCollection(inventoryMemento.getBookCollection());//--make deep copy dont use safe reference *** set the memento from serialized file
		this.replayCommands(invent);// run all the commands
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Command command : commandList) {
			command.execute(inventory);
		}
	}

}
