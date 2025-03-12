package com.project.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.project.auction.repository.DutchAuctionRepo;
import com.project.auction.model.DutchAuction;
import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class DutchAuctionService {

    @Autowired
    private DutchAuctionRepo auctionRepository;

    // Create an auction
    public DutchAuction createAuction(DutchAuction auction) {
        return auctionRepository.save(auction);
    }

    // Place a bid
    public DutchAuction placeBid(Long auctionId, double bidAmount, String bidderName) {
        DutchAuction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found"));
        
        if (bidAmount > auction.getCurrentPrice()) {
            auction.setCurrentPrice(bidAmount);
            auction.setHighestBidder(bidderName);
            return auctionRepository.save(auction);
        } else {
            throw new BidTooLowException("Bid must be higher than the current price");
        }
    }

    // Get auction by item name
    public DutchAuction getAuctionByItemName(String itemName) {
        return auctionRepository.findByItemName(itemName);
    }

    // Get all auctions
    public List<DutchAuction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    // Paginated auctions
    public Page<DutchAuction> getPaginatedAuctions(int page, int size) {
        return auctionRepository.findAll(PageRequest.of(page, size));
    }

    // Check auction status and handle when it ends
    public void checkAuctionStatus(DutchAuction auction) {
        if (auction.getEndTime().isBefore(LocalDateTime.now()) && !auction.isSold()) {
            auction.setIsSold(true);
            auctionRepository.save(auction);
        }
    }

    // Decrement auction price over time (Dutch auction style)
    public void decrementAuctionPrice(DutchAuction auction) {
        if (auction.getEndTime().isAfter(LocalDateTime.now())) {
            LocalDateTime auctionStartTime = auction.getStartTime();
            long minutesElapsed = ChronoUnit.MINUTES.between(auctionStartTime, LocalDateTime.now());
            double priceDecrement = minutesElapsed * auction.getDecrementAmount();
            auction.setCurrentPrice(Math.max(0, auction.getStartingPrice() - priceDecrement));
            auctionRepository.save(auction);
        }
    }
}
