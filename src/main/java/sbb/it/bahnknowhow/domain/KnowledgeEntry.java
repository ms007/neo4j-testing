package sbb.it.bahnknowhow.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class KnowledgeEntry {
    @Id
    @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Relationship(type = "IS_PARTOF")
    private KnowledgeEntry parent;

    public boolean hasParent() {
        return parent != null;
    }

    public void setParent(KnowledgeEntry knowledgeEntry) {
        this.parent = knowledgeEntry;
    }

    @JsonIgnore
    public KnowledgeEntry getParent() {
        return parent;
    }
}
