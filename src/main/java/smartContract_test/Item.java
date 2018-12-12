package smartContract_test;

import java.util.UUID;

public class Item {
	private String uniqueID = UUID.randomUUID().toString();
	private String name;
	private String description;
	
	public Item(String name, String description){
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
