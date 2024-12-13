package com.example.eval_java.controller;

import com.example.eval_java.dao.SalarieDao;
import com.example.eval_java.model.Salarie;
import com.example.eval_java.securite.SalarieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salaries")
public class SalarieController {

    @Autowired
    SalarieDao salarieDao;

    @Autowired
    private SalarieService salarieService;


    @GetMapping
    public ResponseEntity<List<Salarie>> getAllSalaries() {
        List<Salarie> salaries = salarieService.getAllSalaries();
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Salarie> getSalarieById(@PathVariable Integer id) {
        Optional<Salarie> salarie = salarieDao.findById(id);
        if (salarie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(salarie.get(), HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<Salarie> createSalarie(@RequestBody @Valid Salarie salarie) {
        salarie.setId(null);
        salarieDao.save(salarie);
        return new ResponseEntity<>(salarie, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Salarie> updateSalarie(@RequestBody @Valid Salarie salarieDetails, @PathVariable Integer id) {
        Optional<Salarie> optionalSalarie = salarieDao.findById(id);

        if (optionalSalarie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        salarieDetails.setId(id);
        salarieDao.save(salarieDetails);
        return new ResponseEntity<>(salarieDetails, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Salarie> deleteSalarie(@PathVariable Integer id) {
        Optional<Salarie> optionalSalarie = salarieDao.findById(id);

        if (optionalSalarie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        salarieDao.deleteById(id);
        return new ResponseEntity<>(optionalSalarie.get(), HttpStatus.OK);
    }
}

