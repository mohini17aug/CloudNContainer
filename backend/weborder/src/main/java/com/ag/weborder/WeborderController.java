package com.ag.weborder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import com.ag.common.OrderStorage;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webOrder")
@CrossOrigin(origins = "*")
public class WeborderController {
   
    @PostMapping
    // public String handleWebOrder(@RequestBody String orderDetails) {
    //     int randomNum = ThreadLocalRandom.current().nextInt(10000, 99998 + 1);
    //     return "Order Received: " + orderDetails+", Order id: "+randomNum;
    // }
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        // Generate a 5-digit order ID
        int orderId = ThreadLocalRandom.current().nextInt(10000, 99998 + 1);
        // Get the name from the request (or set it to a static value)
        String name = request.getName() != null ? request.getName() : "Paneer and Cheese";

        // Save order details in a temporary storage (e.g., in-memory storage)
        OrderStorage.saveOrder(orderId, name);
        System.out.println("name: "+name);
        System.out.println("orderId: "+orderId);
        System.out.println("name from storage: "+OrderStorage.getOrderName(orderId));

        return new OrderResponse(orderId, name);
    }

    @GetMapping("/{orderId}/name")
    public ResponseEntity<String> getOrderName(@PathVariable int orderId) {
        String order = OrderStorage.getOrderName(orderId);
        System.out.println("name: "+order);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }
}


class OrderRequest {
    private String name;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class OrderResponse {
    private int orderId;
    private String name;

    public OrderResponse(int orderId, String name) {
        this.orderId = orderId;
        this.name = name;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }
}