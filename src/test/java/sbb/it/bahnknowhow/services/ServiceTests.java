package sbb.it.bahnknowhow.services;

import com.google.common.collect.Iterables;
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
import sbb.it.bahnknowhow.repository.KnowledgeEntriesRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@DataNeo4jTest
public class ServiceTests {

    @Autowired
    private KnowledgeEntriesRepository repository;

    @Before
    public void initializeDatabase() {
        System.out.println("seeding embedded database");
    }

    @Test
    @DirtiesContext
    public void testFindAll() {
        System.out.println("findByTitle");

        var knowledgeEntry = new KnowledgeEntry();
        knowledgeEntry.setTitle("Bahnknowhow");

        repository.save(knowledgeEntry);

        var result = repository.findAll();
        assertNotNull(result);

        var size = Iterables.size(result);
        assertEquals(1, size);
    }
}