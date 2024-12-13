package com.example.eval_java.securite;

import com.example.eval_java.dao.ConventionDao;
import com.example.eval_java.model.Convention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConventionService {

    @Autowired
    private ConventionDao conventionDao;

    // Récupérer toutes les conventions
    public List<Convention> getAllConventions() {
        return conventionDao.findAll();
    }

    // Récupérer une convention par ID
    public Convention getConventionById(Integer id) {
        return conventionDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Convention non trouvée avec l'id : " + id));
    }

}

