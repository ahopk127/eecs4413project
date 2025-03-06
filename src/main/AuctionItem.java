
public class AuctionItem {
    private Long id;
    private String description;
    private double shippingPrice;
    private double currentPrice;
    private String highestBidder;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
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

    // toString
    @Override
    public String toString() {
        return "AuctionItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", shippingPrice=" + shippingPrice +
                ", currentPrice=" + currentPrice +
                ", highestBidder='" + highestBidder + '\'' +
                '}';
    }
}
