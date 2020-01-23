package sbb.it.bahnknowhow.models;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class KnowledgeEntry {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    private String title;
}
