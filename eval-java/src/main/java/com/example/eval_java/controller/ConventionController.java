package com.example.eval_java.controller;

import com.example.eval_java.dao.ConventionDao;
import com.example.eval_java.model.Convention;
import com.example.eval_java.securite.ConventionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conventions")
public class ConventionController {

    @Autowired
    ConventionService conventionService;

    @Autowired
    ConventionDao conventionDao;

    @GetMapping
    public ResponseEntity<List<Convention>> getAllConventions() {
        List<Convention> conventions = conventionService.getAllConventions();
        return new ResponseEntity<>(conventions, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Convention> getConventionById(@PathVariable Integer id) {
        Convention convention = conventionService.getConventionById(id);
        return new ResponseEntity<>(convention, HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<Convention> createConvention(@RequestBody @Valid Convention convention) {
        convention.setId(null);

        conventionDao.save(convention);

        return new ResponseEntity<>(convention, HttpStatus.CREATED);
    }

    // Endpoint : Mettre à jour une convention
    @PutMapping("/update/{id}")
    public ResponseEntity<Convention> updateConvention(
          @RequestBody @Valid Convention conventionDetails, @PathVariable Integer id) {

        conventionDetails.setId(id);
        conventionDetails.setNom(conventionDetails.getNom());
        conventionDetails.setSubvention(conventionDetails.getSubvention());
        conventionDetails.setSalarieMaximum(conventionDetails.getSalarieMaximum());
        conventionDetails.setEntreprise(conventionDetails.getEntreprise());
        Optional<Convention> optionalConvention = conventionDao.findById(id);

        if(optionalConvention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        conventionDao.save(conventionDetails);
        return new ResponseEntity<>(optionalConvention.get(), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Convention> deleteConvention(@PathVariable Integer id) {

        Optional<Convention> optionalConvention = conventionDao.findById(id);

        if(optionalConvention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        conventionDao.deleteById(id);
        return new ResponseEntity<>(optionalConvention.get(), HttpStatus.OK);
    }
}
