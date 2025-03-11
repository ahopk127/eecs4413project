// Controller: AuctionController.java
package ca.yorku.eecs4413project.Eecs4413projectApplication;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;


@Component
@Path("/auction")
public class DutchAuctionController {

    @GET
    @Path("/dutch-auction")
    @Produces(MediaType.TEXT_HTML)
    public String getAuctionPage(Model model) {
        model.addAttribute("itemDescription", "A beautiful vintage clock");
        model.addAttribute("shippingPrice", "$10.00");
        model.addAttribute("currentPrice", "$100.00");
        return "dutchAuction";
    }

    @POST
    @Path("/buy-now")
    @Produces(MediaType.TEXT_HTML)
    public String buyNow(Model model) {
        model.addAttribute("message", "Auction ended! Please proceed to payment.");
        return "auctionEnded";
    }
}
