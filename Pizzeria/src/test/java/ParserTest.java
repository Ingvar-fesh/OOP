import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class ParserTest {
    private final Path filePath;

    ParserTest() {
        this.filePath = Paths.get("Pizzeria.json");
    }

    @Test
    public void parserTest() {
        Configuration configuration = new Configuration();
        configuration = getConfiguration();
        Assertions.assertArrayEquals(configuration.getBakersCookTime(), new int [] {61, 23, 78, 99, 46, 83, 58, 32, 41, 41});
        Assertions.assertArrayEquals(configuration.getDeliverersCapacity(), new int[] {5, 3, 4, 5, 5, 2, 4, 4, 2, 2});
        Assertions.assertEquals(configuration.getMaxQueueCapacity(), 50);
        Assertions.assertEquals(configuration.getCountBakers(), 10);
        Assertions.assertEquals(configuration.getCountDeliverers(), 10);
    }


    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        try {
            Reader reader = Files.newBufferedReader(this.filePath);
            configuration.deserialize(reader);
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return configuration;
    }
}