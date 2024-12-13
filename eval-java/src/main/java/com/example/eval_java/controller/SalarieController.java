package com.example.eval_java.controller;

import com.example.eval_java.dao.ConventionDao;
import com.example.eval_java.dao.SalarieDao;
import com.example.eval_java.model.Convention;
import com.example.eval_java.model.Salarie;
import com.example.eval_java.securite.IsAdmin;
import com.example.eval_java.securite.IsEntreprise;
import com.example.eval_java.service.SalarieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salaries")
public class SalarieController {

    @Autowired
    SalarieDao salarieDao;

    @Autowired
    ConventionDao conventionDao;

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


    @IsEntreprise
    @PostMapping("/add")
    public ResponseEntity<Salarie> createSalarie(@RequestBody @Valid Salarie salarie) {

        salarie.setId(null);


        Convention convention = salarie.getConvention();
        if (convention == null || convention.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        Convention existingConvention = conventionDao.findById(convention.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        long nombreActuelDeSalaries = salarieDao.countByConventionId(existingConvention.getId());
        if (nombreActuelDeSalaries >= existingConvention.getSalarieMaximum()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }


        salarie.setConvention(existingConvention);


        salarieDao.save(salarie);


        return new ResponseEntity<>(salarie, HttpStatus.CREATED);
    }


    @IsEntreprise
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

    @IsEntreprise
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

