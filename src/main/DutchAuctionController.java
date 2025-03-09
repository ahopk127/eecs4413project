import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import AuctionItem; 

@RestController
@RequestMapping("/dutchAuction")
public class DutchAuctionController {

    private final DutchAuctionService auctionService;

    public DutchAuctionController(DutchAuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<AuctionItem> getItemDetails(@PathVariable Long itemId) {
        AuctionItem item = auctionService.getItemDetails(itemId);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/buy/{itemId}")
    public ResponseEntity<String> buyNow(@PathVariable Long itemId) {
        boolean success = auctionService.buyNow(itemId);
        if (success) {
            return ResponseEntity.ok("Auction ended. Proceed to payment.");
        } else {
            return ResponseEntity.status(400).body("Failed to buy item. It may already be sold.");
        }
    }
} 

// Service Layer
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
