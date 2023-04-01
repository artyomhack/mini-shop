package com.anton.eshop.repository;

import com.anton.eshop.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Override
    <S extends ProductEntity> S save(S s);

    @Override
    Optional<ProductEntity> findById(Long aLong);

    @Override
    List<ProductEntity> findAll();

    @Override
    void deleteById(Long aLong);
}
