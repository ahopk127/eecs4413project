package com.project.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.project.auction.service.DutchAuctionService;
import com.project.auction.model.DutchAuction;

@RestController
@RequestMapping("/Dutchauctions")
public class DutchAuctionController {

    @Autowired
    private DutchAuctionService auctionService;

    // Create a new auction
    @PostMapping
    public ResponseEntity<DutchAuction> createAuction(@RequestBody DutchAuction auction) {
        try {
            DutchAuction createdAuction = auctionService.createAuction(auction);
            return new ResponseEntity<>(createdAuction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Place a bid on an auction
    @PutMapping("/{auctionId}/bid")
    public ResponseEntity<DutchAuction> placeBid(@PathVariable Long auctionId, @RequestParam double bidAmount, @RequestParam String bidderName) {
        try {
            DutchAuction updatedAuction = auctionService.placeBid(auctionId, bidAmount, bidderName);
            return new ResponseEntity<>(updatedAuction, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // 400 for invalid bid
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404 if auction not found
        }
    }

    // Get an auction by item name
    @GetMapping("/item/{itemName}")
    public ResponseEntity<DutchAuction> getAuctionByItemName(@PathVariable String itemName) {
        DutchAuction auction = auctionService.getAuctionByItemName(itemName);
        if (auction != null) {
            return new ResponseEntity<>(auction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get all auctions
    @GetMapping
    public List<DutchAuction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }
}
