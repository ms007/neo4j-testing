package sbb.it.bahnknowhow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("sbb.it.bahnknowhow.repository")
public class BahnknowhowApplication {
    public static void main(String[] args) {
        SpringApplication.run(BahnknowhowApplication.class, args);
    }
}
