package sbb.it.bahnknowhow.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sbb.it.bahnknowhow.TestConfiguration;
import sbb.it.bahnknowhow.domain.KnowledgeEntry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@DataNeo4jTest
public class KnowledgeEntriesServiceTests {

    @Autowired
    private KnowledgeEntriesService service;

    @Before
    public void initializeDatabase() {
        System.out.println("seeding embedded database");

        var knowledgeEntry = new KnowledgeEntry();
        knowledgeEntry.setTitle("Bahnknowhow");

        service.save(knowledgeEntry);
    }

    @Test
    @DirtiesContext
    public void getAllKnowledgeEntries() {
        System.out.println("getAllKnowledgeEntries");

        var result = service.getAllKnowledgeEntries();
        assertNotNull(result);

        assertEquals(1, result.size());
    }
}