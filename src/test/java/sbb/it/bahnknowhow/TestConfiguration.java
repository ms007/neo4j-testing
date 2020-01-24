package sbb.it.bahnknowhow;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;

@Configuration
@ComponentScan({"sbb.it.bahnknowhow"})
@EnableTransactionManagement
@AutoConfigurationPackage
public class TestConfiguration {
    @Bean
    public SessionFactory sessionFactory() {
        EmbeddedDriver driver = new EmbeddedDriver(graphDatabaseService());
        return new SessionFactory(driver, "sbb.it.bahnknowhow.domain");
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder(new File("test_graph.db"))
                .newGraphDatabase();

    }

}