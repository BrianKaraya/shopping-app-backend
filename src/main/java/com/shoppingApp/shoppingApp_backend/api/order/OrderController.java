package com.shoppingApp.shoppingApp_backend.api.order;

import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.model.WebOrders;
import com.shoppingApp.shoppingApp_backend.service.WebOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private WebOrderService orderService;

    public OrderController(WebOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<WebOrders> getOrders(@AuthenticationPrincipal LocalUser user){
        return orderService.getOrders(user);

    }
}
