
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auctionapp.model.BidRequest;
import com.auctionapp.model.AuctionItem;
import com.auctionapp.service.AuctionService;

@RestController
@RequestMapping("/forwardbidding")
public class ForwardBiddingController {

    @Autowired
    private AuctionService auctionService;

    // Get item details for bidding page
    @GetMapping("/item/{itemId}")
    public ResponseEntity<AuctionItem> getItemDetails(@PathVariable Long itemId) {
        AuctionItem item = auctionService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }

    // Place a new bid
    @PostMapping("/item/{itemId}/bid")
    public ResponseEntity<String> placeBid(@PathVariable Long itemId, @RequestBody BidRequest bidRequest) {
        boolean bidPlaced = auctionService.placeBid(itemId, bidRequest.getBidPrice(), bidRequest.getBidderId());

        if (bidPlaced) {
            return ResponseEntity.ok("Bid placed successfully!");
        } else {
            return ResponseEntity.badRequest().body("Bid must be higher than current price.");
        }
    }
}
