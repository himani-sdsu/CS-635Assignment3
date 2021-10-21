package com.example.assignment2_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//mighta have taken this incorrect
//Expected behavior : Caretaker knows when to trigger save and undo operations, and stores memento state and orignator's state for the same
//Current behavior: serializing and deserializng memento(inventory) to file and from file resp
//think about this once done if we need change of behavior --> current thoughts : the behavior should be more like of an inventory manager which calls save and unsave operations
//public class CareTaker {
public class SerializationUtil {
	// the obj retrieved from file
//	private String temporaryInventoryFileName = "tempInventory.ser";

//	byte[] buf
	public static void serializeBookCollection(InventoryMemento state) {
		File temporaryInventoryFile = new File(Constants.TEMP_INVENTORY_FILE_NAME);
		File inventoryFile = new File(Constants.INVENTORY_FILE_NAME);
		try {
			FileOutputStream fileStream = new FileOutputStream(Constants.TEMP_INVENTORY_FILE_NAME);
			ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);
			outputStream.writeObject(state);
			fileStream.close();
			outputStream.close();
			temporaryInventoryFile.renameTo(inventoryFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static InventoryMemento deserializeBookCollection() {
		Object object;
		try {
			FileInputStream fin = new FileInputStream(Constants.INVENTORY_FILE_NAME);
			ObjectInputStream oin = new ObjectInputStream(fin);
			object = oin.readObject();
			oin.close();
			fin.close();
			return (InventoryMemento) object;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
