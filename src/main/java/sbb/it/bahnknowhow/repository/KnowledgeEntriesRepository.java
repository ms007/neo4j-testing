package sbb.it.bahnknowhow.repository;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sbb.it.bahnknowhow.domain.KnowledgeEntry;

public interface KnowledgeEntriesRepository extends Neo4jRepository<KnowledgeEntry, Long> {
    KnowledgeEntry findByTitle(@Param("title") String title);
}

