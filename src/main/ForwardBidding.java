@Service
public class ForwardBidding {

    @Autowired
    private AuctionItemRepository itemRepository;

    public AuctionItem getItemForBidding() {
        // Fetch the current item from the repository
        return itemRepository.findById(1L).orElseThrow(() -> new ItemNotFoundException("Item not found"));
    }

    public void placeBid(AuctionItem item, double bidPrice) {
        // Update the item’s bid and highest bidder
        item.setCurrentPrice(bidPrice);
        item.setHighestBidder("New Highest Bidder");
        itemRepository.save(item);
    }
}
