package com.ayush.trading.repositories;

import com.ayush.trading.entities.OptionWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionWeekRepository extends JpaRepository<OptionWeek, Integer> {
}