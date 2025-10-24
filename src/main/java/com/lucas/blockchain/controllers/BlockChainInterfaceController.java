package com.lucas.blockchain.controllers;

import com.lucas.blockchain.models.Block;
import com.lucas.blockchain.services.BlockchainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blockchain")
public class BlockChainInterfaceController {
    private final BlockchainService blockchainService;


    public BlockChainInterfaceController(BlockchainService blockchainService){
        this.blockchainService = blockchainService;
    }

    @GetMapping("/show")
    public String getBlockSequence(Model model){
        List<Block> blocks = blockchainService.getAllBlocks();

        model.addAttribute("blocks", blocks);
        return "show";
    }
}
