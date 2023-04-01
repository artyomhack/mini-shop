package com.anton.eshop.repository;

import com.anton.eshop.data.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Long> {
    @Query("select cart from CartEntity cart join cart.user u where u.id = :user_id")
    CartEntity findByUserId(@Param("user_id") Long user_id);
}
