package com.ag.deliveryconfirmation;

import org.springframework.web.bind.annotation.*;
import com.ag.common.OrderStorage;

@RestController
@RequestMapping("/deliveryConfirmation")

public class DeliveryconfirmationController {
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ConfirmationResponse handleDeliveryConfirmation(@RequestBody ConfirmationRequest request) {
        String name = OrderStorage.getOrderName(request.getOrderId());
        name="Grocery";
        if (name != null) {
           return new ConfirmationResponse("Grocery");
       } else {
           return new ConfirmationResponse("Order not found");
       }
    }
}

class ConfirmationRequest {
    private int orderId;

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}

class ConfirmationResponse {
    private String name;

    public ConfirmationResponse(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }
}
