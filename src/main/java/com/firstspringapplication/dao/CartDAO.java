package com.firstspringapplication.dao;

import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.Status;
import com.firstspringapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CartDAO extends JpaRepository <Cart, Integer> {

   List<Cart> findAllByUserId(Integer userId);

 // @Query(nativeQuery = true, value = "SELECT status")
 // List<Status> findOpenCartsByUserId(Integer userId);

   @Query (nativeQuery = true, value = "SELECT * FROM carts WHERE user_id =:id AND time BETWEEN :fromDate and :toDate")
   List<Cart> findCartBetweenDates(Integer id, Date fromDate, Date toDate);

}
