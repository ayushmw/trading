package com.ayush.trading.repositories;

import com.ayush.trading.entities.Nifty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiftyRepository extends JpaRepository<Nifty, Integer> {
}