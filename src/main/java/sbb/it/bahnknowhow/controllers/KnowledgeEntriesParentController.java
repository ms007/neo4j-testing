package sbb.it.bahnknowhow.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sbb.it.bahnknowhow.domain.KnowledgeEntry;
import sbb.it.bahnknowhow.repository.KnowledgeEntriesRepository;

import java.net.URI;

@RestController()
public class KnowledgeEntriesParentController {
    private final KnowledgeEntriesRepository repository;

    KnowledgeEntriesParentController(KnowledgeEntriesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/knowledgeentries/{id}/parent")
    public KnowledgeEntry getParentKnowledgeEntry(@PathVariable("id") long id) {
        // ToDo custum response
        // https://mkyong.com/spring-boot/spring-rest-error-handling-example/
        // https://www.baeldung.com/exception-handling-for-rest-with-spring
        var entry = repository.findById(id);
        if (entry.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KnowledgeEntry Not Found");
        }

        var knowledgeEntry = entry.get();
        if (!knowledgeEntry.hasParent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KnowledgeEntry Parent Not Found");
        }

        return knowledgeEntry.getParent();
    }

    @PostMapping("/knowledgeentries/{id}/parent")
    ResponseEntity<KnowledgeEntry> setParentKnowledgeEntry(@PathVariable("id") long id, @RequestBody KnowledgeEntry knowledgeEntry) {

        var entry = repository.findById(id);
        if (entry.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KnowledgeEntry Not Found");
        }

        var parentEntry = repository.findById(knowledgeEntry.getId());
        if (parentEntry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KnowledgeEntry Parent does not exist");
        }

        var currentKnowledgeEntry = entry.get();
        var parent = parentEntry.get();
        currentKnowledgeEntry.setParent(parent);

        repository.save(currentKnowledgeEntry);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location)
                .body(parent);

    }
}
