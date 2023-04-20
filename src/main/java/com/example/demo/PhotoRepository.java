package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("photoTable")
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}