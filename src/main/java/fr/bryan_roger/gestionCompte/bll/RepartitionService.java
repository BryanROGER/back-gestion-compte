package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.Household;
import fr.bryan_roger.gestionCompte.bo.Income;
import fr.bryan_roger.gestionCompte.bo.Repartition;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import fr.bryan_roger.gestionCompte.dal.RepartitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepartitionService {

    @Autowired
    private RepartitionRepository repartitionRepository;

    public ResponseAPI<List<Repartition>> getAllByHousehold(Household household) {
        var repatitions = repartitionRepository.findByHousehold(household);
        return ResponseApiService.createInstance("200", "Répartitions OK", repatitions);
    }
}
