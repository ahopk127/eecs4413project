package com.project.auction.repository;

import com.project.auction.model.DutchAuction;
import com.project.auction.model.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.time.LocalDateTime;

public interface DutchAuctionRepo extends JpaRepository<DutchAuction, Long> {
    
    // Find by item name
    List<DutchAuction> findByItemName(String itemName);
    
    // Find auctions by status and that are not sold
    List<DutchAuction> findByNotSold(AuctionStatus status);
    
    // Find auctions ongoing (between start and end times)
    List<DutchAuction> findByOngoing(LocalDateTime startTime, LocalDateTime endTime);
    
    // Pagination example by status
    Page<DutchAuction> findByStatus(AuctionStatus status, Pageable pageable);
}
