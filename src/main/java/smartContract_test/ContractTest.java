package smartContract_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import smartContract_test.EthereumConstants;

public class ContractTest {  
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private Web3j web3j;
	private User owner;
	private int timelimit;
	
	public ContractTest(Web3j web3j, User owner, int timelimit) {
		super();
		this.web3j = web3j;
		this.owner = owner;
		this.timelimit = timelimit;
	}
	
	public void deploy(){
	    log.info("Deploying smart contract");
	    try{
		    Helloworld contract = Helloworld.deploy(
		            web3j, owner.getCredential(),
					EthereumConstants.GAS_PRICE, 
					EthereumConstants.GAS_LIMIT_GREETER_TX , java.math.BigInteger.valueOf(timelimit)).send();
		    String contractAddress = contract.getContractAddress();
		    log.info("Smart contract deployed to address " + contractAddress);
		
		    log.info("Initial value of counter in Smart contract: " + contract.getCounter().send());
		    log.info("Incrementing counter in Smart contract");
		    contract.add().send();
		    log.info("Value of counter in Smart contract after increment : " + contract.getCounter().send());
		    log.info("Decrementing counter in Smart contract,");
		    contract.subtract().send();
		    log.info("Final value of counter in Smart contract : " + contract.getCounter().send());
	    }catch(Exception e){
	    	System.out.println("mort aux try catch");
	    }
	}
}
