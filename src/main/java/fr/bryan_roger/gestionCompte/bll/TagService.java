package fr.bryan_roger.gestionCompte.bll;

import fr.bryan_roger.gestionCompte.bo.Tag;
import fr.bryan_roger.gestionCompte.dal.TagRepository;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TagService extends CrudGenericService<Tag, UUID>  {

    public TagService(TagRepository tagRepository) {
        super(tagRepository, "libellé");
    }
}
