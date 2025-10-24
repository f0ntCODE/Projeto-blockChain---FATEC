package com.lucas.blockchain.controllers;

import com.lucas.blockchain.models.Block;
import com.lucas.blockchain.models.Transaction;
import com.lucas.blockchain.services.BlockchainService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blockchain")
public class BlockchainController {

    private final BlockchainService blockchainService;

    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        return blockchainService.addTransaction(transaction);
    }

}
