import org.springframework.stereotype.Service;
import AuctionItem; // Importing the existing AuctionItem class

@Service
public class DutchAuctionService {

    public AuctionItem getItemDetails(Long itemId) {
        // Mock item for now
        return new AuctionItem(itemId, "Sample Item", 20.0, 200.0, "JohnDoe");
    }

    public boolean buyNow(Long itemId) {
        // Terminate auction and return success status
        System.out.println("Auction for item " + itemId + " ended.");
        return true;
    }
} 
