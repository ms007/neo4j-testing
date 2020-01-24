package sbb.it.bahnknowhow.domain;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class KnowledgeEntryParents {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    @Relationship(type = "IS_PARTOF")
    private KnowledgeEntryParents parent;

    public boolean hasParent() {
        return parent != null;
    }
}
