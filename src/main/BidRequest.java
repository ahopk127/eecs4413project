// BidRequest.java
package com.auctionapp.model;

public class BidRequest {
    private Long itemId;
    private double bidPrice;
    private String bidderId;

    // Getters and Setters

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getBidderId() {
        return bidderId;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }

    // toString
    @Override
    public String toString() {
        return "BidRequest{" +
                "itemId=" + itemId +
                ", bidPrice=" + bidPrice +
                ", bidderId='" + bidderId + '\'' +
                '}';
    }
}
