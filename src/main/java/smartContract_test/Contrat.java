package smartContract_test;

import org.web3j.protocol.Web3j;

public class Contrat {

	private User user1;
	private User user2;
	private Item item1;
	private Item item2;
	Trade trade;
	
	public Contrat(User user1, User user2, Item item1, Item item2) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.item1 = item1;
		this.item2 = item2;
	}
	public void deploy(Web3j web3j){
		String clause1 = user1.getName() + " donne " + item1.getName() + " à " + user2.getName();
		String clause2 = user2.getName() + " donne " + item2.getName() + " à " + user1.getName();
    	try {
			trade = Trade.deploy(web3j, user1.getCredential(), EthereumConstants.GAS_PRICE, EthereumConstants.GAS_LIMIT_GREETER_TX, user1.getCredential().getAddress(), user2.getCredential().getAddress(), item1.getUniqueID(), item2.getUniqueID(), clause1, clause2).send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void signer(){

	}
	
	public boolean isSigned(){
		try{
			return trade.isSigned().send();
		}
		catch(Exception e){
			System.out.println("erreur");
			return false;
		}
	}
	
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public Item getItem1() {
		return item1;
	}
	public void setItem1(Item item1) {
		this.item1 = item1;
	}
	public Item getItem2() {
		return item2;
	}
	public void setItem2(Item item2) {
		this.item2 = item2;
	}
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	
}
