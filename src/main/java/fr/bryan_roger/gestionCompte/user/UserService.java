package fr.bryan_roger.gestionCompte.user;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.responseApi.ResponseApiService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseAPI<List<User>> getAllUsers() {
        var users = userRepository.findAll();
        return ResponseApiService.createInstance("200", "Tous les users ont été chargé avec succès", users);
    }

    public ResponseAPI<User> getUserById(String id) {
        try {
            var idUser = UUID.fromString(id);
            var userfound = userRepository.getReferenceById(idUser);
            return ResponseApiService.createInstance("200", "L'utilisateur chargé avec succès", userfound);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun utilisateur trouvé à l'ID transmis : " + id, null);
        }
    }

    public ResponseAPI<User> createOrUpdateUser(User user) {
        // Si id = 0 on create
        if (user.getId() == null || user.getId().toString().isEmpty()) {
            var userToCreate = userRepository.save(user);
            return ResponseApiService.createInstance("201", "Utilisateur créé", userToCreate);
        }
        // sinon on modifie
        try {
            User userToUpdate = userRepository.findById(user.getId()).orElseThrow();
            userToUpdate.setBackgroundColor(user.getBackgroundColor());
            userToUpdate.setLetterColor(user.getLetterColor());
            userToUpdate.setFirstname(user.getFirstname());
            userToUpdate.setLastname(user.getLastname());
            userToUpdate.setWallet(user.getWallet());
            userToUpdate = userRepository.save(userToUpdate);
            return ResponseApiService.createInstance("202", "L'utilisateur à été mis à jour", userToUpdate);

        } catch (Exception e) {
            return ResponseApiService.createInstance("402", "Aucun utilisateur trouvé à l'ID transmis : " + user.getId(), null);
        }
    }

    public ResponseAPI<User> deleteUser(String id) {
        try {
            // s'assurer que l'id est un long
            var idUser = UUID.fromString(id);
            // vérifier si un user avec l'id demandé existe
            userRepository.deleteById(idUser);
            return ResponseApiService.createInstance("204", "L'utilisateur a été supprimé avec succès", null);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun utilisateur trouvé à l'ID transmis : " + id, null);
        }
    }
}
