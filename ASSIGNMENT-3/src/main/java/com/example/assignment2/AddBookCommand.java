package com.example.assignment2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AddBookCommand implements Command, Serializable {
	private Book book;

	public AddBookCommand(Book book) {
		super();
		this.book = book;
	}

	@Override
	public void execute(InventoryImpl inventory) {
		// TODO Auto-generated method stub
		inventory.addBook(book);// adds book (saves inventory memento and generate new command file if needed
								// i.e if state has been saved thrice)
		//in above line we have called this method from inventorydecorator and this call goes to inventoryImpl, 
		//which will add book to this inventory (and write the inventory memento if needed and delete the command file) and then
		//write the new command to the file
		// write command to file
		//should go to decorator.. since decorator will save the command
		FileOutputStream fileStream;
		try {
			fileStream = new FileOutputStream(Constants.COMMAND_FILE_NAME, true);
			ObjectOutputStream objStream = new ObjectOutputStream(fileStream);
			objStream.writeObject(this);
			objStream.close();
			fileStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
