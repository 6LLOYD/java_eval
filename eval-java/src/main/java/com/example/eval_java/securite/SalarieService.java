package com.example.eval_java.securite;

import com.example.eval_java.dao.SalarieDao;
import com.example.eval_java.model.Salarie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalarieService {

    @Autowired
    private SalarieDao salarieDao;


    public List<Salarie> getAllSalaries() {
        return salarieDao.findAll();
    }


    public Salarie getSalarieById(Integer id) {
        return salarieDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Salarie non trouvé avec l'id : " + id));
    }


    public Salarie createSalarie(Salarie salarie) {
        return salarieDao.save(salarie);
    }


    public Salarie updateSalarie(Integer id, Salarie salarieDetails) {
        Salarie salarie = salarieDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Salarie non trouvé avec l'id : " + id));

        salarie.setMatricule(salarieDetails.getMatricule());
        salarie.setCodeBarre(salarieDetails.getCodeBarre());
        salarie.setConvention(salarieDetails.getConvention());

        return salarieDao.save(salarie);
    }


    public void deleteSalarie(Integer id) {
        Salarie salarie = salarieDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Salarie non trouvé avec l'id : " + id));

        salarieDao.delete(salarie);
    }
}
