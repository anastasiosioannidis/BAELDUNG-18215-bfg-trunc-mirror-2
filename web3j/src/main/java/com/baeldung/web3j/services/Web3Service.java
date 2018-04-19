package com.baeldung.web3j.services;

import com.baeldung.web3j.contracts.Example;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.baeldung.web3j.constants.Constants.DEFAULT_ADDRESS;
import static com.baeldung.web3j.constants.Constants.GENERIC_EXCEPTION;
import static com.baeldung.web3j.constants.Constants.PLEASE_SUPPLY_REAL_DATA;

@Service
public class Web3Service {

    private Web3j web3j = Web3j.build(new HttpService());

    //Create and Init the default Web3J connection
    public void customInit(String provider) {
        this.web3j = Web3j.build(new HttpService(provider));
    }

    //Convert to and from supplied contract ABI bytecode
    public static String toBinary(String bytecode) {
        return bytecode.replaceFirst("^0x", "");
    }

    public static String toByteCode(String binary) {
        return "0x" + binary;
    }

    @Async
    public CompletableFuture<EthBlockNumber> getBlockNumber() {
        EthBlockNumber result = new EthBlockNumber();
        try {
            this.web3j.ethBlockNumber().sendAsync().thenApply(r -> r.getBlockNumber());
        } catch (Exception ex) {
            System.out.println(GENERIC_EXCEPTION);
        }
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<EthAccounts> getEthAccounts() {
        EthAccounts result = new EthAccounts();
        try {
            this.web3j.ethAccounts().sendAsync().thenApply(r -> r.getAccounts());
        } catch (Exception ex) {
            System.out.println(GENERIC_EXCEPTION);
        }
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<EthGetTransactionCount> getTransactionCount() {
        EthGetTransactionCount result = new EthGetTransactionCount();
        try {
            this.web3j.ethGetTransactionCount(DEFAULT_ADDRESS, DefaultBlockParameter.valueOf("latest")).sendAsync().thenApply(r -> r.getTransactionCount());
        } catch (Exception ex) {
            System.out.println(GENERIC_EXCEPTION);
        }
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<EthGetBalance> getEthBalance() {
        EthGetBalance result = new EthGetBalance();
        try {
            this.web3j.ethGetBalance(DEFAULT_ADDRESS, DefaultBlockParameter.valueOf("latest")).sendAsync().thenApply(r -> r.getBalance());
        } catch (Exception ex) {
            System.out.println(GENERIC_EXCEPTION);
        }
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<String> fromScratchContractExample() {

        String contractAddress = "";

        try {
            //Create a wallet
            WalletUtils.generateNewWalletFile("PASSWORD", new File("/path/to/destination"), true);
            //Load the credentials from it
            Credentials credentials = WalletUtils.loadCredentials("PASSWORD", "/path/to/walletfile");

            //Deploy contract to address specified by wallet
            Example contract = Example.deploy(this.web3j,
                    credentials,
                    ManagedTransaction.GAS_PRICE,
                    Contract.GAS_LIMIT).send();

            //Het the address
            contractAddress = contract.getContractAddress();

        } catch (Exception ex) {
            System.out.println(PLEASE_SUPPLY_REAL_DATA);
            return CompletableFuture.completedFuture(PLEASE_SUPPLY_REAL_DATA);
        }
        return CompletableFuture.completedFuture(contractAddress);
    }

    @Async
    public CompletableFuture<String> sendTx() {
        String transactionHash = "";

        try {
            List inputParams = new ArrayList();
            List outputParams = new ArrayList();
            Function function = new Function("fuctionName", inputParams, outputParams);

            String encodedFunction = FunctionEncoder.encode(function);

            BigInteger nonce = BigInteger.valueOf(100);
            BigInteger gasprice = BigInteger.valueOf(100);
            BigInteger gaslimit = BigInteger.valueOf(100);

            Transaction transaction = Transaction.createFunctionCallTransaction("FROM_ADDRESS", nonce, gasprice, gaslimit, "TO_ADDRESS", encodedFunction);

            org.web3j.protocol.core.methods.response.EthSendTransaction transactionResponse = web3j.ethSendTransaction(transaction).sendAsync().get();
            transactionHash = transactionResponse.getTransactionHash();

        } catch (Exception ex) {
            System.out.println(PLEASE_SUPPLY_REAL_DATA);
            return CompletableFuture.completedFuture(PLEASE_SUPPLY_REAL_DATA);
        }

        return CompletableFuture.completedFuture(transactionHash);
    }
}








