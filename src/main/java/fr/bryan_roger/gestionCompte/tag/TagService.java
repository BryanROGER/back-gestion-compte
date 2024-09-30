package fr.bryan_roger.gestionCompte.tag;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import fr.bryan_roger.gestionCompte.responseApi.ResponseApiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public ResponseAPI<List<Tag>> getAllTags(){
        var tags = tagRepository.findAll();
        return ResponseApiService.createInstance("200", "Tous les tags ont été transmis avec succès", tags);
    }

    public ResponseAPI<Tag> getTagById(String id){
        var idTag = UUID.fromString(id);
        var tag = tagRepository.getReferenceById(idTag);
        return ResponseApiService.createInstance("200", "Le tag est transmit avec succès", tag);
    }

    public ResponseAPI<Tag> createOrUpdateTag(Tag tag){
        if (tag.getId() == null || tag.getId().toString().isEmpty()){
            var newTag = tagRepository.save(tag);
            return ResponseApiService.createInstance("201", "Le tag à été créé avec succès", newTag);
        }
        var tagToUpdate = tagRepository.getReferenceById(tag.getId());
        tagToUpdate.setLabel(tag.getLabel());
        tagToUpdate = tagRepository.save(tagToUpdate);
        return ResponseApiService.createInstance("202", "Le tag a été modifié avec succès", tagToUpdate);
    }

    public ResponseAPI<Tag> deleteTag(String id){
        var idTag = UUID.fromString(id);
        var tagToDelete = tagRepository.getReferenceById(idTag);
        tagRepository.delete(tagToDelete);
        return ResponseApiService.createInstance("204", "Tag supprimé avec succès", null);
    }



}
