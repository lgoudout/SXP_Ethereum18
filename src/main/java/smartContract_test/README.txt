##Download and Install Geth
Dowload Geth at the following URI : https://geth.ethereum.org/downloads/

In the user's home directory, create a folder : C:\Users\currentUser> mkdir myBlockChain\chaindata\
Copy file "genesis.json" from "...\SXP_Ethereum18\src\main\java\smartContract_test\"
	to "C:\Users\currentUser\myBlockChain\"

Install Geth in the same folder : "C:\Users\currentUser\myBlockChain\"

Now in your command prompt, from this folder, initiate the blockChain protocol with the following : 
"cd \Users\currentUser\myBlockChain\"
"geth --datadir=./chaindata/ init ./genesis.json"

Finally, to start your local BlockChain, run the command :
"geth --datadir=./chaindata/ --rpc console" ( --rpcaddr "127.0.0.1")

The blockChain is ready !
To make it works, you need to create an ethereum accounts, which is a keystore in ethereum with private and public key pair.
(You can create one easily using Mist, available at the following link : https://github.com/ethereum/mist/releases?after=0.4.0)

To mine, use the following command in the sale terminal "miner.start();".
Use "miner.stop();" to stop mining.