package sbb.it.bahnknowhow.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sbb.it.bahnknowhow.repository.KnowledgeEntriesRepository;
import sbb.it.bahnknowhow.domain.KnowledgeEntry;
import sbb.it.bahnknowhow.services.KnowledgeEntriesService;

import java.net.URI;
import java.util.List;

@RestController
public class KnowledgeEntriesController {
    private final KnowledgeEntriesService service;
    private final KnowledgeEntriesRepository repository;

    KnowledgeEntriesController(KnowledgeEntriesService service, KnowledgeEntriesRepository repository) {
        this.service = service;
        this.repository = repository;
    }


    @GetMapping("/knowledgeentries")
    public List<KnowledgeEntry> getKnowledgeEntries() {
        return service.getAllKnowledgeEntries();
    }

    @GetMapping("/knowledgeentries/{id}")
    public KnowledgeEntry getById(@PathVariable("id") long id) {
        var entry = repository.findById(id, 0);
        if (entry.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KnowledgeEntry Not Found");
        }

        return entry.get();
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
