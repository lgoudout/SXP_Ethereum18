package smartContract_test;


import org.omg.CORBA.Environment;
import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;


public class Wallet {
	String DIRECTORY_DOWNLOADS;
	
	public Wallet(String directory){
		DIRECTORY_DOWNLOADS = directory;
	}
	
    public String createWallet() throws Exception {
        String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getPath();
        String fileName = WalletUtils.generateLightNewWalletFile("password", new File(path));
        return path + fileName;
    }

    public Credentials loadCredentials(String password) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials(
                password,
                "/storage/emulated/0/Download/UTC--2018-03-16T19-05-15.125--833e56c5df2a654372a252658006af4d3158e9f3.json");
        return credentials;
    }

    public Web3j constructWeb3(String URL) throws IOException {
        Web3j web3 = Web3jFactory.build(new HttpService(URL));  // defaults to http://localhost:8545/
        Web3ClientVersion web3ClientVersion;
        web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        Log.i("Web3 verison", clientVersion);
        return web3;
    }

    public String sendTransaction(Web3j web3, Credentials credentials) throws Exception {
        TransactionReceipt transferReceipt = Transfer.sendFunds(web3, credentials,
                "0x19e03255f667bdfd50a32722df860b1eeaf4d635",  // you can put any address here
                BigDecimal.ONE, Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
                .send();
        return transferReceipt.getTransactionHash();
    }

    public String createBipWallet() throws Exception {
        String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).getPath();
        Bip39Wallet bip39Wallet = WalletUtils.generateBip39Wallet("password", new File(path));
        String filename = bip39Wallet.getFilename();
        String mnemonic = bip39Wallet.getMnemonic();
        return "Success";
        
        log.info("Sending Ether ..");
        TransactionReceipt transferReceipt = Transfer.sendFunds(
                web3j, credentials,
                "0x00f1768d874cd7ce314dad7924bd6a281bcd47ed",  
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

log.info("Sending Ether ..");
TransactionReceipt transferReceipt = Transfer.sendFunds(
        web3j, credentials,
        "0x00f1768d874cd7ce314dad7924bd6a281bcd47ed",  
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