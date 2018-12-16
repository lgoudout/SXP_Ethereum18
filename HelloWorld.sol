pragma solidity ^0.4.16;

contract HelloWorld {
   uint256 counter = 5;
   uint256 day;
   uint256 expiration;
   
   function HelloWorld(uint256 date_delta) public{
		day = now;
		expiration = now + date_delta;
   }
   
   function add() public {  //increases counter by 1
       if(now <= expiration){
		counter++;
	   }
	   else{selfdestruct();}

   }
 
   function subtract() public { //decreases counter by 1
		if(now <= expiration){
			counter--;
		}
		else{selfdestruct();}
   }
   
   function getCounter() public constant returns (uint256) {
       return counter;
   } 


   
   
}