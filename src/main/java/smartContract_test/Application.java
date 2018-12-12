package smartContract_test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    
    public static void main(String[] args) throws Exception {
        new Application().run();
    }

  
    
    private User createUser(String name, String password,  String walletDirectory){
    	User user;
        File file = new File(walletDirectory);
        String walletFileName = null;
		try {
			walletFileName = WalletUtils.generateFullNewWalletFile("password", file);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException
				| CipherException | IOException e) {
			e.printStackTrace();
		}
        Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials("password", walletDirectory+ "\\" + walletFileName);
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}     
        user = new User(name, password, credentials);
    	return user;
    }
    
    /* Pour nos test, quand on crée un compte ethereum, il n'y a pas d'Ether ou de gas disponible, ce qui pose problème quand on tente d'effectuer des transaction, 
     * on charge donc juste un compte special qui possede de quoi transferer au nouveau compte pour eviter de devoir attendre que chaque nouveau compte est miné suffisamment
     */
    private Credentials getCredentialBankForTest(String filePath, String password){
        Credentials credentialsTest = null;
    	try {
			credentialsTest = WalletUtils.loadCredentials(password, filePath);
		} catch (IOException | CipherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return credentialsTest;
    }
    
    private void fillUserAccount(Web3j web3j, ArrayList<User> userList, Credentials credentialsTest){
        for(int i = 0; i < userList.size()-2; i++){
        	TransactionReceipt transferReceipt;
			try {
				transferReceipt = Transfer.sendFunds(
				        web3j, credentialsTest,
				        userList.get(i).getCredential().getAddress(),  
				        BigDecimal.valueOf(20), Convert.Unit.ETHER)  
				        .sendAsync().get();
	        	log.info("Transaction complete : "+ transferReceipt.getTransactionHash());
			} catch (InterruptedException | ExecutionException | IOException | TransactionException e) {
	        	log.info("Transaction failed");
				e.printStackTrace();
			}
        }
    }
    
    private void run() throws Exception {
        String usersHomeDir = System.getProperty("user.home");
    	BufferedReader brBlockChainConfig = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\main\\java\\smartContract_test\\blockchain.config"));
    	String password = brBlockChainConfig.readLine();
    	String keystore = brBlockChainConfig.readLine();
    	String keystore2 = brBlockChainConfig.readLine();
    	brBlockChainConfig.close();
    	
        Web3j web3j = Web3j.build(new HttpService());
        log.info("Connected to Ethereum client version: "+ web3j.web3ClientVersion().send().getWeb3ClientVersion());
        String walletDirectory = usersHomeDir + "\\myblockchain\\chaindata\\keystore";
    	ArrayList<User> userList = new ArrayList<User>();
    	
    	System.out.println("Création des utilisateurs Florent, Lenaic, Julien, Jeremy");
    	userList.add(createUser("Florent", "password", walletDirectory));
    	userList.add(createUser("Jeremy", "password", walletDirectory));
    	userList.add(createUser("Julien", "password", walletDirectory));
    	userList.add(createUser("Lenaic", "password", walletDirectory));
    	
    	for(int i =0; i < userList.size(); i++){
        	System.out.println(userList.get(i).getName() + ", voici ta clé privé : " + userList.get(i).getCredential().getEcKeyPair().getPrivateKey().toString());
        	System.out.println("et voila ta clé public : " + userList.get(i).getCredential().getEcKeyPair().getPublicKey().toString());
    	}
    	
    	Credentials credentialTest = getCredentialBankForTest(walletDirectory + "\\" + keystore, "password");
    	fillUserAccount(web3j, userList, credentialTest);
    	
    	userList.get(0).addItem(new Item("banane", "un fruit de couleur jaune"));
    	userList.get(1).addItem(new Item("cerise", "un fruit de couleur rouge"));
    	userList.get(2).addItem(new Item("kiwi", "un fruit de couleur verte"));
    	userList.get(3).addItem(new Item("orange", "un fruit de couleur orange"));
    	
    	System.out.println("test deploiement d'un contrat");
    	ContractTest dummyContract = new ContractTest(web3j,userList.get(0),200);
    	dummyContract.deploy();
    	
    	Contrat contrat = new Contrat(userList.get(0), userList.get(1), userList.get(0).getItem(0), userList.get(1).getItem(0));
    	System.out.println("deploiement du smartcontract");
    	contrat.deploy(web3j);
    	System.out.println("succès");
    	System.out.println("user 1 signe le contrat");
    	TransactionReceipt trade1 = Trade.load(contrat.getTrade().getContractAddress(), web3j, userList.get(0).getCredential(), 					EthereumConstants.GAS_PRICE, 
					EthereumConstants.GAS_LIMIT_GREETER_TX).sign().send();
    	System.out.println("user 2 signe le contrat");
    	TransactionReceipt trade2 = Trade.load(contrat.getTrade().getContractAddress(), web3j, userList.get(1).getCredential(), 					EthereumConstants.GAS_PRICE, 
					EthereumConstants.GAS_LIMIT_GREETER_TX).sign().send();
    	
//    	System.out.println("statut : " + Trade.load(contrat.getTrade().getContractAddress(), web3j, userList.get(0).getCredential(), 					EthereumConstants.GAS_PRICE, 
//				EthereumConstants.GAS_LIMIT_GREETER_TX).isSigned().send().toString());

    }
    
}