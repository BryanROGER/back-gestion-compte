package fr.bryan_roger.gestionCompte.home;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.responseApi.ResponseApiService;
import fr.bryan_roger.gestionCompte.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HomeService {

    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public ResponseAPI<Home> getHomeById(String id) {
        try {
            var idHome = UUID.fromString(id);
            var homeFound = homeRepository.getReferenceById(idHome);
            return ResponseApiService.createInstance("200", "L'utilisateur chargé avec succès", homeFound);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun utilisateur trouvé à l'ID transmis : " + id, null);
        }
    }
}
