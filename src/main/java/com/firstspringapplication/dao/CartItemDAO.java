package com.firstspringapplication.dao;

import com.firstspringapplication.model.Cart;
import com.firstspringapplication.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CartItemDAO extends JpaRepository<CartItem, Integer> {

    List<CartItem> findAllByCart(Cart cart);

    @Query(nativeQuery = true,
            value = "" +
                    "SELECT SUM(r.it_price * r.it_amount) FROM " +
                    "(SELECT ci.amount AS it_amount, i.price AS it_price " +
                    "FROM carts c " +
                    "JOIN cart_items ci ON c.id = ci.cart_id " +
                    "JOIN items i ON ci.item_id = i.id " +
                    "WHERE c.user_id =:userId " +
                    "AND c.time BETWEEN :dateFrom and :dateTo " +
                    ") AS r")
    Integer findTotalSum(Integer userId, Date dateFrom, Date dateTo);

    @Query(nativeQuery = true,
            value = "SELECT SUM(r.it_price * r.it_amount) FROM " +
                            "(SELECT i.price AS it_price, ci.amount AS it_amount " +
                            "FROM cart_items ci " +
                            "JOIN items i ON ci.item_id = i.id" +
                            "WHERE ci.cart_id = :cartID ) AS r")
    Integer findSumOfCart(Integer cartID);
}


// "SELECT SUM(r.it_price * r.it_amount) FROM " +
//         "(SELECT ci.amount AS it_amount, i.price AS it_price " +
//         "FROM cart_items ci " +
//         "JOIN items i ON ci.item_id = i.id " +
//         "WHERE ci.cart_id = :cartID)" +
//         "AS r")