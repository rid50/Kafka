package org.yaruss.kafka.spring.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Slice<Image> findAllBy(Pageable pageable);
}
