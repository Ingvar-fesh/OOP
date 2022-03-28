import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;

public class PizzeriaConfiguration {
    private int maxQueueCapacity; // maximum possible capacity of the shared queue
    private int countBakers; // number of bakers
    private int[] bakersCookTime; // time cooking for each baker
    private int countDeliverers; // number of deliverers
    private int[] deliverersCapacity; // maximum bag capacity for each deliverer
    private transient Gson gson;

    /**
     * Default constructor to initialize internal structures.
     */
    public PizzeriaConfiguration() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        this.gson = builder.create();
    }

    /**
     * <p>Constructor to initialize new <code>PizzeriaConfigurator</code> with the specified values.</p>
     *
     * @param maxQueueCapacity Maximum possible capacity of the shared queue.
     * @param countBakers Number of pizzaiolos.
     * @param bakersCookTime Cooking time for each pizzaiolo.
     * @param countDeliverers Number of deliverers.
     * @param deliverersCapacity Bag capacity for each deliverer.
     */
    public PizzeriaConfiguration(int maxQueueCapacity, int countBakers, int[] bakersCookTime, int countDeliverers, int[] deliverersCapacity) {
        this();
        this.maxQueueCapacity = maxQueueCapacity;
        this.countBakers = countBakers;
        this.bakersCookTime = bakersCookTime;
        this.countDeliverers = countDeliverers;
        this.deliverersCapacity = deliverersCapacity;
    }


    public int getMaxQueueCapacity() {
        return maxQueueCapacity;
    }


    public int[] getBakersCookTime() {
        return this.bakersCookTime;
    }

    public int[] getDeliverersCapacity() {
        return this.deliverersCapacity;
    }

    /**
     * <p>Serializes content of this <code>PizzeriaConfigurator</code> to the specified writer.</p>
     *
     * @param writer Writer stream for serialization.
     */
    public void serialize(Writer writer) {
        gson.toJson(this, writer);
    }

    /**
     * <p>Deserializes content of this <code>PizzeriaConfigurator</code> to the specified reader.</p>
     *
     * @param reader Reader stream for deserialization.
     */
    public void deserialize(Reader reader) {
        PizzeriaConfiguration pizzeriaConfigurator = gson.fromJson(reader, PizzeriaConfiguration.class);
        this.maxQueueCapacity = pizzeriaConfigurator.maxQueueCapacity;
        this.countBakers = pizzeriaConfigurator.countBakers;
        this.bakersCookTime = pizzeriaConfigurator.bakersCookTime;
        this.countDeliverers = pizzeriaConfigurator.countDeliverers;
        this.deliverersCapacity = pizzeriaConfigurator.deliverersCapacity;
    }
}
