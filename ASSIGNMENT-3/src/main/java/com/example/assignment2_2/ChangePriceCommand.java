package com.example.assignment2_2;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChangePriceCommand implements Command, Serializable {
	private String bookName;
	private Float bookPrice;

	public ChangePriceCommand(String bookName, Float bookPrice) {
		super();
		this.bookName = bookName;
		this.bookPrice = bookPrice;
	}

	@Override
	public void execute(InventoryImpl inventory) {
		// TODO Auto-generated method stub
		try {
			inventory.changePrice(bookName, bookPrice);
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
