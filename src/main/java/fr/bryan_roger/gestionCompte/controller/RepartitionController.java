package fr.bryan_roger.gestionCompte.controller;

import fr.bryan_roger.gestionCompte.bll.RepartitionService;
import fr.bryan_roger.gestionCompte.bo.Household;
import fr.bryan_roger.gestionCompte.bo.Repartition;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import fr.bryan_roger.gestionCompte.dal.RepartitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/repartitions")
@RestController
@CrossOrigin
public class RepartitionController {

    private final RepartitionService repartitionService;

    public RepartitionController(RepartitionService repartitionService) {
        this.repartitionService = repartitionService;
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseAPI<List<Repartition>>> getAllRepartitions(@RequestBody Household household) {
        var repartitions = repartitionService.getAllByHousehold(household);
        return ResponseEntity.ok(repartitions);
    }
}
