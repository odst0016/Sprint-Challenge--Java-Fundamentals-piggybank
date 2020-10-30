package com.odst0016.piggybank.controllers;

import com.odst0016.piggybank.models.Coins;
import com.odst0016.piggybank.repositories.CoinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinsController {
    @Autowired
    CoinsRepository coinsRepository;

    private List<Coins> filterCoins(List<Coins> myList, CheckCoins tester) {
        List<Coins> testList = new ArrayList<>();
        for(Coins c : myList) {
            if (tester.test(c)) {
                testList.add(c);
            }
        }
        return testList;
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<?> apiInfo() {
        return new ResponseEntity<>("API is live", HttpStatus.OK);
    }

    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> coinTotal() {
        List<Coins> myList = new ArrayList<>();
        coinsRepository.findAll().iterator().forEachRemaining(myList::add);
        double total = 0;
        for (Coins c : myList) {
            total = total + (c.getValue() * c.getQuantity());
        }

        for(Coins c : myList) {
            System.out.print((c.getQuantity()) + " ");
            if(c.getQuantity() > 1) {
                System.out.print(c.getNameplural() + "\n");
            } else {
                System.out.print(c.getName() + "\n");
            }

        }
        System.out.print("The piggy bank holds " + total);

        return new ResponseEntity<>(total, HttpStatus.OK);


    }
}
