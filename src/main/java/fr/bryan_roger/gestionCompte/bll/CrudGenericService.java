package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.Budget;
import fr.bryan_roger.gestionCompte.bo.Income;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import fr.bryan_roger.gestionCompte.bo.Spend;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


@Transactional
public abstract class CrudGenericService<T, ID> {

    protected final JpaRepository<T, ID> repository;
    protected final String entityName;

    protected CrudGenericService(JpaRepository<T, ID> repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }

    public ResponseAPI<List<T>> getAll() {
        List<T> entities = repository.findAll();
        return ResponseApiService.createInstance("200", "Les " + entityName + " ont été chargés avec succès", entities);
    }

    public ResponseAPI<T> getById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun " + entityName + " trouvé pour l'ID : " + id));
        return ResponseApiService.createInstance("200", "Le " + entityName + " a été chargé avec succès", entity);
    }

    public ResponseAPI<T> create(T entity) {

        if(entity instanceof Spend){
            ((Spend) entity).setOrder(new Date().getTime());
        }

        if(entity instanceof Income){
            ((Income) entity).setOrder(new Date().getTime());
        }


        T newEntity = repository.save(entity);
        return ResponseApiService.createInstance("201", "Le " + entityName + " a été créé avec succès", newEntity);
    }

    public ResponseAPI<T> update(ID id, T updatedEntity) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Aucun " + entityName + " trouvé pour l'ID : " + id);
        }
        T savedEntity = repository.save(updatedEntity);
        return ResponseApiService.createInstance("202", "Le " + entityName + " a été mis à jour avec succès", savedEntity);
    }

    public ResponseAPI<T> delete(ID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Aucun " + entityName + " trouvé pour l'ID : " + id);
        }
        repository.deleteById(id);
        return ResponseApiService.createInstance("204", "Le " + entityName + " a été supprimé avec succès", null);
    }

}
