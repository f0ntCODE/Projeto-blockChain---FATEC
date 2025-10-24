package com.lucas.blockchain.services;


import com.lucas.blockchain.models.Block;
import com.lucas.blockchain.models.Blockchain;
import com.lucas.blockchain.models.Transaction;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class BlockchainService {


    private List<Block> blocks = new ArrayList<>();
    private final Blockchain blockchain;
    private List<Transaction> pendingTransaction = new ArrayList<>();
    private final int blockSizeLimit = 3;

    public BlockchainService(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public ResponseEntity<String> addTransaction(Transaction transaction) {

        pendingTransaction.add(transaction);
        if (pendingTransaction.size() >= blockSizeLimit) {
            log.info("block size complete, create new block");
            createBlock();
        }
        log.info("Sending from {} to {} with a value of {} has been added, soon the block will be created", transaction.getSender(), transaction.getRecipient(), String.format("%.8f",transaction.getAmount()));
        return ResponseEntity.ok("Transaction added, then a block will be created");
    }

    private void createBlock() {

        Block previusBlock = blockchain.getChain().get(blockchain.getChain().size() - 1);
        Block newBlock = new Block();
        newBlock.setIndex(previusBlock.getIndex() + 1);
        newBlock.setPreviousHash(previusBlock.getHash());
        newBlock.setTransactions(pendingTransaction);
        newBlock.setTimestamp(System.currentTimeMillis());
        newBlock.setHash(calculateHash(newBlock));

        //adicionar o novo bloco a uma arrayList
        blocks.add(newBlock);

        blockchain.getChain().add(newBlock);
        log.info("Block created with translations: {}. Its hash is: {}", newBlock.getTransactions(), newBlock.getHash());
        showBlocks(blocks);
        pendingTransaction.clear();
    }

    public List<Block> getAllBlocks(){
     return blocks;
    }

    /**
     * EXPERIMENTAL: VISUALIZAÇÃO LEGÍVEL
     * Este método visa permitir uma visualização mais legível dos blocos criados, porém, via terminal
     *
     * @param blockList A lista de blocos criados
     */

    private void showBlocks(List<Block> blockList){

        System.out.println("LISTA DE BLOCOS: ");
        for(Block b : blockList) {
           // System.out.println("\nPARÂMETROS: " + b.toString() + "\n\n");

            System.out.println("\n\t BLOCO: " +
                    "\n|ÍNDICE: " + b.getIndex() +
                    "\n|HASH ANTERIOR: " + b.getPreviousHash() +
                    "\n|HASH GERADO: " + b.getHash() +
                    "\n|TIMESTAMP: " + b.getTimestamp() +
                    "\n|TRANSAÇÕES: " + b.getTransactions() +
                    "\n");
        }

    }

    private String calculateHash(Block newBlock) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String blockData = newBlock.getIndex() + newBlock.getTimestamp() + newBlock.getPreviousHash() + newBlock.getTransactions(); // Concatenação dos dados do bloco
            byte[] hash = digest.digest(blockData.getBytes());

            // Converter o hash de bytes para uma representação hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

}
