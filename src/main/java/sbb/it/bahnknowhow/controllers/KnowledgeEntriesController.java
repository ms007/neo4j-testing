package sbb.it.bahnknowhow.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sbb.it.bahnknowhow.data.KnowledgeEntriesRepository;
import sbb.it.bahnknowhow.models.KnowledgeEntry;

import java.net.URI;

@RestController
public class KnowledgeEntriesController {
    private final KnowledgeEntriesRepository repository;

    KnowledgeEntriesController(KnowledgeEntriesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/knowledgeentries")
    public Iterable<KnowledgeEntry> getKnowledgeEntries() {
        return repository.findAll();
    }

    @PostMapping("/knowledgeentries")
    ResponseEntity<KnowledgeEntry> createKnowledgeEntry(@RequestBody KnowledgeEntry knowledgeEntry) {

        var createdKnowledgeEntry = repository.save(knowledgeEntry);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdKnowledgeEntry.getId()).toUri();

        return ResponseEntity.created(location)
                .body(createdKnowledgeEntry);

    }
}
