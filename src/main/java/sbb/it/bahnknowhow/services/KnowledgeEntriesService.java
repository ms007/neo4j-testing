package sbb.it.bahnknowhow.services;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import sbb.it.bahnknowhow.domain.KnowledgeEntry;
import sbb.it.bahnknowhow.repositories.KnowledgeEntriesRepository;

import java.util.List;

@Service
public class KnowledgeEntriesService {
    private final KnowledgeEntriesRepository repository;

    KnowledgeEntriesService(KnowledgeEntriesRepository repository) {
        this.repository = repository;
    }

    public List<KnowledgeEntry> getAllKnowledgeEntries() {
        return Lists.newArrayList(repository.findAll());
    }

    public KnowledgeEntry save(KnowledgeEntry knowledgeEntry) {
        return repository.save(knowledgeEntry);
    }
}
