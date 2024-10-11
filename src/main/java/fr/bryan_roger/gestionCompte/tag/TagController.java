package fr.bryan_roger.gestionCompte.tag;

import fr.bryan_roger.gestionCompte.responseApi.ResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/tags")
@RestController
@CrossOrigin
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseAPI<List<Tag>>> tags() {
        var responseTags = tagService.getAllTags();
        return ResponseEntity.ok(responseTags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Tag>> getTag(@PathVariable String id) {
        var responseTag = tagService.getTagById(id);
        return ResponseEntity.ok(responseTag);
    }

    @PutMapping("/add")
    public ResponseEntity<ResponseAPI<Tag>> addTag(@RequestBody Tag tag) {
        var responseTagToUpdate = tagService.createOrUpdateTag(tag);
        return ResponseEntity.ok(responseTagToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseAPI<Tag>> deleteTag(@PathVariable String id) {
        var responseTagToDelete = tagService.deleteTag(id);
        return ResponseEntity.ok(responseTagToDelete);
    }


}
