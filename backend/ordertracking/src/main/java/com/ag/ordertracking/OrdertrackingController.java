package com.ag.ordertracking;


import org.springframework.web.bind.annotation.*;

import com.ag.common.OrderStorage;

@RestController
@RequestMapping("/orderTracking")
public class OrdertrackingController {
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public TrackingResponse  handleOrderTracking(@RequestBody TrackingRequest  request) {
         String name = OrderStorage.getOrderName(request.getOrderId());
         name="Grocery";
         if (name != null) {
            return new TrackingResponse("Grocery");
        } else {
            return new TrackingResponse("Order not found");
        }
    }
}

class TrackingRequest {
    private int orderId;

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}

class TrackingResponse {
    private String name;

    public TrackingResponse(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }
}