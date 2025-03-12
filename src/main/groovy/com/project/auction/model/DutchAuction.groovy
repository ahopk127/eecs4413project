package com.project.auction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
public class DutchAuction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private double startingPrice;
    private double currentPrice;
    private String highestBidder;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double decrementAmount; // Amount by which price decreases
    private boolean isSold; // Whether the auction is sold
    
    private AuctionStatus status; // Auction status

    public enum AuctionStatus {
        CREATED, STARTED, ENDED, SOLD
    }

    // Constructor
    public DutchAuction(String itemName, double startingPrice, double decrementAmount) {
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.decrementAmount = decrementAmount;
        this.currentPrice = startingPrice;
        this.isSold = false;
        this.status = AuctionStatus.CREATED;
    }

    @PrePersist
    public void prePersist() {
        if (startTime == null) {
            startTime = LocalDateTime.now();
        }
    }

    // Method to update the current price based on the time elapsed
    public void updatePrice() {
        if (status == AuctionStatus.STARTED && !isSold) {
            long elapsedMinutes = java.time.Duration.between(startTime, LocalDateTime.now()).toMinutes();
            currentPrice = startingPrice - (elapsedMinutes * decrementAmount);
            if (currentPrice <= 0) {
                currentPrice = 0; // The price can't be negative
                endAuction();
            }
        }
    }

    // Method to handle the auction end
    public void endAuction() {
        if (!isSold) {
            this.status = AuctionStatus.ENDED;
        }
    }

    // Method to handle a bid
    public boolean placeBid(String bidderName) {
        if (currentPrice > 0 && !isSold) {
            this.highestBidder = bidderName;
            this.isSold = true;
            this.status = AuctionStatus.SOLD;
            return true;
        }
        return false;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(String highestBidder) {
        this.highestBidder = highestBidder;
    }

    public LocalDateTime getStar
