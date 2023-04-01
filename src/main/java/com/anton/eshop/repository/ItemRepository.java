package com.anton.eshop.repository;

import com.anton.eshop.data.ItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
    @Query(value = "select i.* from carts c, items i, users u, cart_item ci where u.id=:user_id and " +
            "c.user_id=u.id and c.id=ci.cart_id and i.id=ci.item_id", nativeQuery = true)
    Iterable<ItemEntity> findByUserId(String user_id);

//    @Query(value = "delete from cart_item where item_id in (:ids) and cart_id = :cartId", nativeQuery = true)
//    void deleteCartItemJoinById(List<Long> ids, Long cartid);
}
