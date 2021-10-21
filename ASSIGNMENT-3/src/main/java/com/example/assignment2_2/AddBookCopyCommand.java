package com.example.assignment2_2;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AddBookCopyCommand implements Command, Serializable {
	private String bookName;
	private Integer numberOfCopy;

	public AddBookCopyCommand(String bookName, Integer numberOfCopy) {
		super();
		this.bookName = bookName;
		this.numberOfCopy = numberOfCopy;
	}

	@Override
	public void execute(InventoryImpl inventory) {
		// TODO Auto-generated method stub
		try {
			inventory.addCopy(bookName, numberOfCopy);
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
