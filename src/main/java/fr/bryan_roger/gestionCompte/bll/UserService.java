package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.User;
import fr.bryan_roger.gestionCompte.dal.UserRepository;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public ResponseAPI<User> getUserById(String email) {
        try {
            var userfound = userRepository.getReferenceById(email);
            return ResponseApiService.createInstance("200", "L'utilisateur chargé avec succès", userfound);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis (Long)", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun utilisateur trouvé à l'ID transmis : " + email, null);
        }
    }

//    public ResponseAPI<User> createOrUpdateUser(User user) {
//        // Si id = "" on create
//        if (user.getEmail() == null || user.getEmail().toString().isEmpty()) {
//            var userToCreate = userRepository.save(user);
//            return ResponseApiService.createInstance("201", "Utilisateur créé", userToCreate);
//        }
//        // sinon on modifie
//        try {
//            User userToUpdate = userRepository.findById(user.getId()).orElseThrow();
//            userToUpdate.setBackgroundColor(user.getBackgroundColor());
//            userToUpdate.setLetterColor(user.getLetterColor());
//            userToUpdate.setFirstname(user.getFirstname());
//            userToUpdate.setLastname(user.getLastname());
//            userToUpdate.setWallet(user.getWallet());
//            userToUpdate = userRepository.save(userToUpdate);
//            return ResponseApiService.createInstance("202", "L'utilisateur à été mis à jour", userToUpdate);
//
//        } catch (Exception e) {
//            return ResponseApiService.createInstance("402", "Aucun utilisateur trouvé à l'ID transmis : " + user.getId(), null);
//        }
//    }

    public ResponseAPI<User> deleteUser(String email) {
        try {
            // vérifier si un user avec l'id demandé existe
            userRepository.deleteById(email);
            return ResponseApiService.createInstance("204", "L'utilisateur a été supprimé avec succès", null);
        } catch (NumberFormatException e) {
            return ResponseApiService.createInstance("401", "L'identifiant n'est dans le format requis", null);
        } catch (EntityNotFoundException e) {
            return ResponseApiService.createInstance("402", "Aucun utilisateur trouvé à l'ID transmis : " + email, null);
        }
    }
}
