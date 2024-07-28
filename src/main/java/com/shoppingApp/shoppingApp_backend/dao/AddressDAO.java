package com.shoppingApp.shoppingApp_backend.dao;

import com.shoppingApp.shoppingApp_backend.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDAO extends ListCrudRepository<Address,Long> {
}
