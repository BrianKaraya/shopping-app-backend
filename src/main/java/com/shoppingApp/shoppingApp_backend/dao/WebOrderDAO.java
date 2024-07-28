package com.shoppingApp.shoppingApp_backend.dao;

import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.model.WebOrders;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface WebOrderDAO extends ListCrudRepository<WebOrders,Long> {



    List<WebOrders> findByUser(LocalUser user);
}
