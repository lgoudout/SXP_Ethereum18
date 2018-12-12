package smartContract_test;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
//import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

public class Application2 {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    
   public void createAccount(){
	    try {
	        String password = "secr3t";
	        ECKeyPair keyPair = Keys.createEcKeyPair();
	        WalletFile wallet = new WalletFile();
	        WalletUtils.generateWalletFile(password, keyPair, null, true);
	        
	        System.out.println("Private key: " + keyPair.getPrivateKey().toString(16));
	        System.out.println("Account: " + wallet.getAddress());

	    } catch(Exception e) {
	        System.err.println("Error: " + e.getMessage());
	    }
   }
    
    
    public static void main(String[] args) throws Exception {
        new Application2().run();
    }

    private void run() throws Exception {
    	
    	String usersHomeDir = System.getProperty("user.home");

    	BufferedReader brBlockChainConfig = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\smartContract_test\\blockchain.config"));
    	String password = brBlockChainConfig.readLine();
    	String keystore = brBlockChainConfig.readLine();
    	String keystore2 = brBlockChainConfig.readLine();
    	brBlockChainConfig.close();
    	
    	File f = new File(usersHomeDir + "\\myBlockChain\\genesis.json");
    	if(f.exists() && !f.isDirectory()) { 
    	    System.out.println("Local blockChain already exists.");
    	}
    	else {
    		System.out.println("Local blockChain doesn't exists, creating file...");
    		FileUtils.copyFile(new File(System.getProperty("user.dir") + "\\src\\main\\java\\smartContract_test\\genesis.json"),
    					new File(usersHomeDir + "\\myBlockChain\\genesis.json"));
    	}
    	
    	String walletDirectory = usersHomeDir + "\\myBlockChain\\chaindata\\keystore";
        Web3j web3j = Web3j.build(new HttpService());
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());
        File filebch = new File(walletDirectory);
        String walletFileName = WalletUtils.generateFullNewWalletFile(password, filebch);
        Credentials credentials = WalletUtils.loadCredentials(password, walletDirectory+ "\\" + walletFileName);
        System.out.println(credentials.getAddress());
        Credentials credentialsTest =
                WalletUtils.loadCredentials(
                		password,//mdp attention
                        usersHomeDir + "\\myBlockChain\\chaindata\\keystore\\" + keystore);
        log.info("Credentials loaded");
        log.info("Sending Ether ..");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentialsTest,
                credentials.getAddress(),  
                BigDecimal.valueOf(20), Convert.Unit.ETHER)  
                .sendAsync().get();
        log.info("Transaction complete : "
                + transferReceipt.getTransactionHash());


        // Now lets deploy a smart contract
        log.info("Deploying smart contract");
        Helloworld contract = Helloworld.deploy(
                web3j, credentials,
                ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT , java.math.BigInteger.valueOf(200)).send();
        String contractAddress = contract.getContractAddress();
        log.info("Smart contract deployed to address " + contractAddress);

        log.info("Initial value of counter in Smart contract: " + contract.getCounter().send());
        log.info("Incrementing counter in Smart contract");
        contract.add().send();
        log.info("Value of counter in Smart contract after increment : " + contract.getCounter().send());
        log.info("Decrementing counter in Smart contract, je m'endors");
        log.info("je me reveil");
        contract.subtract().send();
        try{
        	log.info("Final value of counter in Smart contract : " + contract.getCounter().send());
        }
        catch(Exception e){
        	System.out.println("Contrat expiré, suppression du contrat");
        }

    }
}