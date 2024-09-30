package fr.bryan_roger.gestionCompte.spend;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.responseApi.ResponseApiService;
import fr.bryan_roger.gestionCompte.services.FormatDateForDatabase;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SpendService {

    private final SpendRepository spendRepository;

    private final FormatDateForDatabase formatDateForDatabase;

    public SpendService(SpendRepository spendRepository, FormatDateForDatabase formatDateForDatabase) {
        this.spendRepository = spendRepository;
        this.formatDateForDatabase = formatDateForDatabase;
    }

    public ResponseAPI<List<Spend>> getAllSpends() {
        var spends = spendRepository.findAll();
        return ResponseApiService.createInstance("200","Les dépenses ont été chargé avec succès", spends);
    }

    public ResponseAPI<Spend> getSpendById(String id) {
        var idSpend = UUID.fromString(id);
        var spend = spendRepository.getReferenceById(idSpend);
        return ResponseApiService.createInstance("200","La dépense a été chargée avec succès", spend);
    }

    public ResponseAPI<Spend> createOrUpdateSpend(Spend spend) {
        // Si id = 0 on create
        if (spend.getId() == null || spend.getId().toString().isEmpty()) {
            var spendToCreate = spendRepository.save(spend);
            return ResponseApiService.createInstance("201", "dépense créée", spendToCreate);
        }
        // sinon on modifie
        try {
            Spend spendToUpdate = spendRepository.findById(spend.getId()).orElseThrow();
            spendToUpdate.setDate(spend.getDate());
            spendToUpdate.setName(spend.getName());
            spendToUpdate.setTag(spend.getTag());
            spendToUpdate.setAmount(spend.getAmount());
            spendToUpdate.setPayer(spend.getPayer());
            spendToUpdate.setRecipients(spend.getRecipients());
            spendRepository.save(spendToUpdate);
            return ResponseApiService.createInstance("202", "La dépense à été mis à jour", spendToUpdate);

        } catch (Exception e) {
            return ResponseApiService.createInstance("402", "Aucune dépense trouvé à l'ID transmis : " + spend.getId(), null);
        }
    }

    public ResponseAPI<Spend> deleteSpend(String id) {
        try {
            var idSpend = UUID.fromString(id);
            // vérifier si un revenu avec l'id demandé existe
            spendRepository.deleteById(idSpend);
            return ResponseApiService.createInstance("204", "La dépense a été supprimée avec succès", null);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucune dépense trouvé à l'ID transmis : " + id, null);
        }
    }

    public ResponseAPI<List<Spend>> getSpendsInAMonth(String month, String year) {
        try {
            final var DATE = formatDateForDatabase.convertDateToCorrectFormatDatabase(month, year);
            var spendsOfMonth = spendRepository.findByDate(DATE);
            return ResponseApiService.createInstance("200",String.format("Les dépenses du mois de %s ont été chargées avec succès", month), spendsOfMonth);
        } catch (Exception e) {
            return ResponseApiService.createInstance("401", String.format("Erreur dans la récupération des mois de %s ", month), null);
        }
    }

}
