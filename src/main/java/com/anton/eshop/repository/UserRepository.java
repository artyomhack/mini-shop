package com.anton.eshop.repository;

import com.anton.eshop.data.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Override
    <S extends UserEntity> S save(S s);

    @Override
    Optional<UserEntity> findById(Long aLong);

    @Override
    Iterable<UserEntity> findAll();

    @Override
    void deleteById(Long aLong);
}
