package com.example.assignment2_2;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SellBookCommand implements Command, Serializable {
	private String bookName;

	public SellBookCommand(String bookName) {
		super();
		this.bookName = bookName;
	}

	@Override
	public void execute(InventoryImpl inventory) {
		// TODO Auto-generated method stub
		try {
			inventory.sellBook(bookName);
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
