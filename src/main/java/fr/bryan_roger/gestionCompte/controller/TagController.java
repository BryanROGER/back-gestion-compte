package fr.bryan_roger.gestionCompte.controller;

import fr.bryan_roger.gestionCompte.bo.Tag;
import fr.bryan_roger.gestionCompte.bo.ResponseAPI;
import fr.bryan_roger.gestionCompte.bll.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        var responseTags = tagService.getAll();
        return ResponseEntity.ok(responseTags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAPI<Tag>> getTag(@PathVariable String id) {
        var responseTag = tagService.getById(UUID.fromString(id));
        return ResponseEntity.ok(responseTag);
    }

    @PostMapping()
    public ResponseEntity<ResponseAPI<Tag>> addTag(@RequestBody Tag tag) {
        var responseTagToAdd = tagService.create(tag);
        return ResponseEntity.ok(responseTagToAdd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAPI<Tag>> updateTag(@RequestBody Tag tag, @PathVariable String id) {
        var responseTagToUpdate = tagService.update(UUID.fromString(id), tag);
        return ResponseEntity.ok(responseTagToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAPI<Tag>> deleteTag(@PathVariable String id) {
        var responseTagToDelete = tagService.delete(UUID.fromString(id));
        return ResponseEntity.ok(responseTagToDelete);
    }


}
