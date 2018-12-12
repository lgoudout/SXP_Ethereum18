package smartContract_test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Trade extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506040516109e63803806109e683398101604090815281516020830151918301516060840151608085015160a086015160088054600160a060020a031916339081179182905595979485019593850194928301939190920191600160a060020a0316148061008c5750600054600160a060020a03878116911614155b151561009757600080fd5b60008054600160a060020a031916600160a060020a03881617905583516100c5906001906020870190610128565b5060038054600160a060020a031916600160a060020a03871617905582516100f4906004906020860190610128565b508151610108906006906020850190610128565b50805161011c906007906020840190610128565b505050505050506101c3565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061016957805160ff1916838001178555610196565b82800160010185558215610196579182015b8281111561019657825182559160200191906001019061017b565b506101a29291506101a6565b5090565b6101c091905b808211156101a257600081556001016101ac565b90565b610814806101d26000396000f3006080604052600436106100cf5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630e9032df81146100d457806318a2985b1461017b5780632ca15122146102055780635e01eb5a1461021c57806360ccba551461024d57806367e404ce146102625780636cc7deba1461027757806381ea32a21461028c5780638dc716c5146102a1578063e521c2e9146102b6578063e64653e0146102cb578063e66a6b22146102e0578063ef78053814610309578063f029c24b1461031e575b600080fd5b3480156100e057600080fd5b506100e9610333565b60408051600160a060020a038516815282151591810191909152606060208083018281528551928401929092528451608084019186019080838360005b8381101561013e578181015183820152602001610126565b50505050905090810190601f16801561016b5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561018757600080fd5b506101906103dc565b6040805160208082528351818301528351919283929083019185019080838360005b838110156101ca5781810151838201526020016101b2565b50505050905090810190601f1680156101f75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561021157600080fd5b5061021a610472565b005b34801561022857600080fd5b506102316104e4565b60408051600160a060020a039092168252519081900360200190f35b34801561025957600080fd5b506101906104f3565b34801561026e57600080fd5b50610231610554565b34801561028357600080fd5b50610190610563565b34801561029857600080fd5b506101906105c4565b3480156102ad57600080fd5b50610190610652565b3480156102c257600080fd5b506102316106ad565b3480156102d757600080fd5b506101906106bc565b3480156102ec57600080fd5b506102f561071d565b604080519115158252519081900360200190f35b34801561031557600080fd5b5061023161076d565b34801561032a57600080fd5b506100e961077c565b6000805460018054604080516020600261010085871615026000190190941693909304601f8101849004840282018401909252818152600160a060020a03909416949392918301828280156103c95780601f1061039e576101008083540402835291602001916103c9565b820191906000526020600020905b8154815290600101906020018083116103ac57829003601f168201915b5050506002909301549192505060ff1683565b60018054604080516020601f600260001961010087891615020190951694909404938401819004810282018101909252828152606093909290918301828280156104675780601f1061043c57610100808354040283529160200191610467565b820191906000526020600020905b81548152906001019060200180831161044a57829003601f168201915b505050505090505b90565b6008805473ffffffffffffffffffffffffffffffffffffffff1916331790819055600054600160a060020a03908116911614156104bb576002805460ff191660011790556104e2565b600854600354600160a060020a03908116911614156100cf576005805460ff191660011790555b565b600854600160a060020a031690565b60068054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156104675780601f1061043c57610100808354040283529160200191610467565b600854600160a060020a031681565b60048054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156104675780601f1061043c57610100808354040283529160200191610467565b6006805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561064a5780601f1061061f5761010080835404028352916020019161064a565b820191906000526020600020905b81548152906001019060200180831161062d57829003601f168201915b505050505081565b6007805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561064a5780601f1061061f5761010080835404028352916020019161064a565b600054600160a060020a031690565b60078054604080516020601f60026000196101006001881615020190951694909404938401819004810282018101909252828152606093909290918301828280156104675780601f1061043c57610100808354040283529160200191610467565b60008054600854600160a060020a039081169116148061074d5750600354600854600160a060020a039081169116145b156100cf5760025460ff168015610766575060055460ff165b905061046f565b600354600160a060020a031690565b600380546004805460408051602060026101006001861615026000190190941693909304601f8101849004840282018401909252818152600160a060020a03909416949392918301828280156103c95780601f1061039e576101008083540402835291602001916103c95600a165627a7a72305820f235d45700d41c12aaa6cc2f52d35ae0fd31d8f9c5de1cda038da826abcf9ffe0029";

    public static final String FUNC_MEMBER1 = "member1";

    public static final String FUNC_GETITEM1 = "getItem1";

    public static final String FUNC_SIGN = "sign";

    public static final String FUNC_GETSENDER = "getSender";

    public static final String FUNC_GETCLAUSEA = "getClauseA";

    public static final String FUNC_SENDER = "sender";

    public static final String FUNC_GETITEM2 = "getItem2";

    public static final String FUNC_CLAUSEA = "clauseA";

    public static final String FUNC_CLAUSEB = "clauseB";

    public static final String FUNC_GETADD1 = "getAdd1";

    public static final String FUNC_GETCLAUSEB = "getClauseB";

    public static final String FUNC_ISSIGNED = "isSigned";

    public static final String FUNC_GETADD2 = "getAdd2";

    public static final String FUNC_MEMBER2 = "member2";

    protected Trade(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Trade(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<Tuple3<String, String, Boolean>> member1() {
        final Function function = new Function(FUNC_MEMBER1, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple3<String, String, Boolean>>(
                new Callable<Tuple3<String, String, Boolean>>() {
                    @Override
                    public Tuple3<String, String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<String> getItem1() {
        final Function function = new Function(FUNC_GETITEM1, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> sign() {
        final Function function = new Function(
                FUNC_SIGN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> getSender() {
        final Function function = new Function(
                FUNC_GETSENDER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getClauseA() {
        final Function function = new Function(FUNC_GETCLAUSEA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> sender() {
        final Function function = new Function(FUNC_SENDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getItem2() {
        final Function function = new Function(FUNC_GETITEM2, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> clauseA() {
        final Function function = new Function(FUNC_CLAUSEA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> clauseB() {
        final Function function = new Function(FUNC_CLAUSEB, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getAdd1() {
        final Function function = new Function(FUNC_GETADD1, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getClauseB() {
        final Function function = new Function(FUNC_GETCLAUSEB, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> isSigned() {
        final Function function = new Function(FUNC_ISSIGNED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> getAdd2() {
        final Function function = new Function(FUNC_GETADD2, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple3<String, String, Boolean>> member2() {
        final Function function = new Function(FUNC_MEMBER2, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple3<String, String, Boolean>>(
                new Callable<Tuple3<String, String, Boolean>>() {
                    @Override
                    public Tuple3<String, String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue());
                    }
                });
    }

    public static RemoteCall<Trade> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String add1, String add2, String item1, String item2, String clause1, String clause2) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(add1), 
                new org.web3j.abi.datatypes.Address(add2), 
                new org.web3j.abi.datatypes.Utf8String(item1), 
                new org.web3j.abi.datatypes.Utf8String(item2), 
                new org.web3j.abi.datatypes.Utf8String(clause1), 
                new org.web3j.abi.datatypes.Utf8String(clause2)));
        return deployRemoteCall(Trade.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Trade> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String add1, String add2, String item1, String item2, String clause1, String clause2) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(add1), 
                new org.web3j.abi.datatypes.Address(add2), 
                new org.web3j.abi.datatypes.Utf8String(item1), 
                new org.web3j.abi.datatypes.Utf8String(item2), 
                new org.web3j.abi.datatypes.Utf8String(clause1), 
                new org.web3j.abi.datatypes.Utf8String(clause2)));
        return deployRemoteCall(Trade.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Trade load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Trade(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Trade load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Trade(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
