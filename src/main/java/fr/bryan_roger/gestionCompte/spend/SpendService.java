package fr.bryan_roger.gestionCompte.spend;

import fr.bryan_roger.gestionCompte.income.Income;
import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.responseApi.ResponseApiService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpendService {

    @Autowired
    private SpendRepository spendRepository;

    public ResponseAPI<List<Spend>> getAllSpends() {
        var spends = spendRepository.findAll();
        return ResponseApiService.createInstance("200","Les dépenses ont été chargé avec succès", spends);
    }

    public ResponseAPI<Spend> getSpendById(String id) {
        var idSpend = Long.parseLong(id);
        var spend = spendRepository.getReferenceById(idSpend);
        return ResponseApiService.createInstance("200","La dépense a été chargée avec succès", spend);
    }

    public ResponseAPI<Spend> createOrUpdateSpend(Spend spend) {
        // Si id = 0 on create
        if (spend.getId() == 0) {
            var spendToCreate = spendRepository.save(spend);
            return ResponseApiService.createInstance("201", "dépense créée", spendToCreate);
        }
        // sinon on modifie
        try {
            Spend spendToUpdate = spendRepository.findById(spend.getId()).orElseThrow();
            spendToUpdate.setDate(spend.getDate());
            spendToUpdate.setNom(spend.getNom());
            spendToUpdate.setTag(spend.getTag());
            spendToUpdate.setAmount(spend.getAmount());
            spendToUpdate.setPayer(spend.getPayer());
            spendToUpdate.setRecipients(spend.getRecipients());
            return ResponseApiService.createInstance("202", "La dépense à été mis à jour", spendToUpdate);

        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucune dépense trouvé à l'ID transmis : " + spend.getId(), null);
        } catch (Exception e) {
            return ResponseApiService.createInstance("402", "Aucune dépense trouvé à l'ID transmis : " + spend.getId(), null);
        }
    }

    public ResponseAPI<Income> deleteSpend(String id) {
        try {
            // s'assurer que l'id est un long
            var idIncome = Long.parseLong(id);
            // vérifier si un revenu avec l'id demandé existe
            spendRepository.deleteById(idIncome);
            return ResponseApiService.createInstance("204", "La dépense a été supprimée avec succès", null);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucune dépense trouvé à l'ID transmis : " + id, null);
        }
    }

}
