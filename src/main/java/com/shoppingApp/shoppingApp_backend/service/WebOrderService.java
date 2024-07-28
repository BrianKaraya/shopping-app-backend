package com.shoppingApp.shoppingApp_backend.service;

import com.shoppingApp.shoppingApp_backend.dao.WebOrderDAO;
import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.model.WebOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebOrderService {
    @Autowired
    private WebOrderDAO webOrderDAO;

    public WebOrderService(WebOrderDAO webOrderDAO) {
        this.webOrderDAO = webOrderDAO;
    }

    public List<WebOrders> getOrders(LocalUser user){
        return webOrderDAO.findByUser(user);


    }
}
