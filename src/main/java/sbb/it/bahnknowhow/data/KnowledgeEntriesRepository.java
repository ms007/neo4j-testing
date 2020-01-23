package sbb.it.bahnknowhow.data;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import sbb.it.bahnknowhow.models.KnowledgeEntry;

public interface KnowledgeEntriesRepository extends Neo4jRepository<KnowledgeEntry, Long> {
}

