package com.anton.eshop.repository;

import com.anton.eshop.data.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    <S extends Product> S save(S s);

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    Iterable<Product> findAll();

    @Override
    void deleteById(Long aLong);
}
