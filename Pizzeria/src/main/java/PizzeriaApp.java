import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

public class PizzeriaApp implements Runnable {
    private final Pizzeria pizzeria; // instance of pizzeria class
    private final Random random;
    private final Path filePath; // default pizzeria file
    private final int maxQueueCapacity = 50; // // maximum capacity of the shared queue
    private final int countBakers = 10; // default number of bakers
    private final int bakersCookingTime = 100; // default maximum time cooking for each baker
    private final int countDeliverers = 10; // default number of deliverers
    private final int maxBagCapacity = 5; // default maximum capacity deliverer's bag
    private final int TIME = 10000; // // execution time


    /**
     * Default constructor to initialize internal structures.
     */
    public PizzeriaApp() {
        this.random = new Random();
        this.filePath = Paths.get("Pizzeria.json");
        if (!Files.exists(this.filePath)) {
            createFile();
        }
        this.pizzeria = new Pizzeria(getConfiguration());
    }

    /**
     * Starts all threads for a specified time.
     */
    @Override
    public void run() {
        this.pizzeria.start();
        try {
            Thread.sleep(this.TIME);
        } catch (InterruptedException e) {}
        this.pizzeria.stop();
    }

    /**
     * Creates json file with pizzeria configuration
     */
    private void createFile() {
        try {
            if(!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
            Writer writer = Files.newBufferedWriter(this.filePath);
            int[] bakersCookingTime = new int[this.countBakers];
            int[] deliverersCapacity = new int[this.countDeliverers];
            Arrays.setAll(bakersCookingTime, i -> this.random.nextInt(this.bakersCookingTime) + 1);
            Arrays.setAll(deliverersCapacity, i -> this.random.nextInt(this.maxBagCapacity) + 1);
            Configuration configuration = new Configuration(this.maxQueueCapacity, this.countBakers, bakersCookingTime, this.countDeliverers, deliverersCapacity);
            configuration.serialize(writer);
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates pizzeria configurator from json file.
     */
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
