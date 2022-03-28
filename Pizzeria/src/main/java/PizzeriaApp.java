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
    private final int QUEUE_CAPACITY = 50; // // maximum capacity of the shared queue
    private final int N_BAKERS = 10; // default number of bakers
    private final int BAKERS_COOK_TIME = 100; // default maximum time cooking for each baker
    private final int N_DELIVERERS = 10; // default number of deliverers
    private final int DELIVERERS_CAPACITY = 5; // default maximum capacity deliverer's bag
    private final int TIME = 1000; // // execution time


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
        System.out.printf("%7s |%11s\n", "number", "state");
        System.out.printf("%8s+%12s\n", "--------", "------------");
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
            int[] pizzaiolosCookTime = new int[this.N_BAKERS];
            int[] deliverersCapacity = new int[this.N_DELIVERERS];
            Arrays.setAll(pizzaiolosCookTime, i -> this.random.nextInt(this.BAKERS_COOK_TIME) + 1);
            Arrays.setAll(deliverersCapacity, i -> this.random.nextInt(this.DELIVERERS_CAPACITY) + 1);
            PizzeriaConfiguration pizzeriaConfigurator = new PizzeriaConfiguration(this.QUEUE_CAPACITY, this.N_BAKERS, pizzaiolosCookTime, this.N_DELIVERERS, deliverersCapacity);
            pizzeriaConfigurator.serialize(writer);
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates pizzeria configurator from json file.
     * @return
     */
    private PizzeriaConfiguration getConfiguration() {
        PizzeriaConfiguration pizzeriaConfigurator = new PizzeriaConfiguration();
        try {
            Reader reader = Files.newBufferedReader(this.filePath);
            pizzeriaConfigurator.deserialize(reader);
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return pizzeriaConfigurator;
    }
}
