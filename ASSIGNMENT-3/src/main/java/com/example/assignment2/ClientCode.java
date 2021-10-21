package com.example.assignment2;

public class ClientCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inventory inventory = new InventoryDecorator(new InventoryImpl());
//		Inventory i1 = new InventoryImpl();
		Book b1 = new Book("b1", 50f, 2);
		Book b2 = new Book("b2", 50f, 2);
		Book b3 = new Book("b3", 50f, 2);
		Book b4 = new Book("b4", 50f, 2);
		Book b5 = new Book("b5", 50f, 2);
		Book b6 = new Book("b6", 50f, 2);
		inventory.addBook(b1);
		inventory.addBook(b2);
		inventory.addBook(b3);
		inventory.addBook(b4);
		inventory.addBook(b5);
		inventory.addBook(b6);
	}

}
