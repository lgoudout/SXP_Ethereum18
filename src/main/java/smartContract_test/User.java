package smartContract_test;

import java.util.ArrayList;
import java.util.UUID;

import org.web3j.crypto.Credentials;


public class User {
	
	private String uniqueID = UUID.randomUUID().toString();
	private String name;
	private String password; //pas safe mais prototype donc pas la peine de s'en soucier
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private Credentials credential;
	
	public User(String name, String password, Credentials credential) {
		this.name = name;
		this.setPassword(password);
		this.credential = credential;
	}
	
	public void addItem(Item item){
		itemList.add(item);
	}
	
	public Item getItem(int index){
		if(index < itemList.size())
			return itemList.get(index);
		return null;
	}

	
	public String getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Credentials getCredential() {
		return credential;
	}

	public void setCredential(Credentials credential) {
		this.credential = credential;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public ArrayList<Item> getItemList() {
		return itemList;
	}



	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	
}
