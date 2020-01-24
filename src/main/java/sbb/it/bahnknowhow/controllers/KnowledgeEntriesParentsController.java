package sbb.it.bahnknowhow.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sbb.it.bahnknowhow.domain.KnowledgeEntry;
import sbb.it.bahnknowhow.domain.KnowledgeEntryParents;
import sbb.it.bahnknowhow.repository.KnowledgeEntriesRepository;

@RestController()
public class KnowledgeEntriesParentsController {
    private final KnowledgeEntriesRepository repository;

    KnowledgeEntriesParentsController(KnowledgeEntriesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/knowledgeentries/{id}/parents")
    public KnowledgeEntryParents getAllParents(@PathVariable("id") long id) {
        var entry = repository.findById(id, 10);
        if (entry.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KnowledgeEntry Not Found");
        }

        var knowledgeEntry = entry.get();
        return Map(knowledgeEntry);
    }

    private KnowledgeEntryParents Map(KnowledgeEntry knowledgeEntry) {
        if (knowledgeEntry == null) {
            return null;
        }

        return new KnowledgeEntryParents(){{
            setId(knowledgeEntry.getId());
            setTitle(knowledgeEntry.getTitle());
            setParent(Map(knowledgeEntry.getParent()));
        }};
    }
}
