package com.odst0016.piggybank.repositories;

import com.odst0016.piggybank.models.Coins;
import org.springframework.data.repository.CrudRepository;

public interface CoinsRepository extends CrudRepository<Coins, Long> {
}
