package com.ag.orderconfirmation;


import org.springframework.web.bind.annotation.*;
import com.ag.common.OrderStorage;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orderConfirmation")

public class OrderconfirmationController {
    private RestTemplate restTemplate = new RestTemplate();
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ConfirmationResponse handleOrderConfirmation(@RequestBody ConfirmationRequest request) {
        System.out.println("id is: "+request.getOrderId());
        System.out.println("name: "+OrderStorage.getOrderName(request.getOrderId()));
        int orderId=request.getOrderId();

        String weborderUrl = "http://weborder:8081/webOrder/" + orderId + "/name";
        try {
          String name = restTemplate.getForObject(weborderUrl, String.class);
          System.out.println("name from rest: "+name);
          if (name != null) {
            return new ConfirmationResponse("Grocery");
        } else {
            return new ConfirmationResponse("Grocery");
          //  return new ConfirmationResponse("Order not found");
        }
           
        } catch (Exception e) {
            return new ConfirmationResponse("Grocery");
          //  return null;
        }
        
    //     String name = OrderStorage.getOrderName(request.getOrderId());
    //     if (name != null) {
    //        return new ConfirmationResponse(name);
    //    } else {
    //        return new ConfirmationResponse("Order not found");
    //    }
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
