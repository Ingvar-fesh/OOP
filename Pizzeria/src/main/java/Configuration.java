import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.Writer;

public class Configuration {
    private int maxQueueCapacity; // maximum possible capacity of the shared queue
    private int countBakers; // number of bakers
    private int[] bakersCookTime; // time cooking for each baker
    private int countDeliverers; // number of deliverers
    private int[] deliverersCapacity; // maximum bag capacity for each deliverer
    private final transient Gson gson;


    Configuration() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        this.gson = builder.create();
    }

    Configuration(int maxQueueCapacity, int countBakers, int[] bakersCookTime, int countDeliverers, int[] deliverersCapacity) {
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

    public int getCountBakers() {
        return countBakers;
    }

    public int getCountDeliverers() {
        return countDeliverers;
    }

    public int[] getBakersCookTime() {
        return this.bakersCookTime;
    }

    public int[] getDeliverersCapacity() {
        return this.deliverersCapacity;
    }

    /**
     * Serializes content of this Configuration to the specified writer.
     * @param writer Writer stream for serialization.
     */
    public void serialize(Writer writer) {
        gson.toJson(this, writer);
    }

    /**
     * Deserializes content of this Configuration to the specified reader.
     * @param reader Reader stream for deserialization.
     */
    public void deserialize(Reader reader) {
        Configuration configuration = gson.fromJson(reader, Configuration.class);
        this.maxQueueCapacity = configuration.maxQueueCapacity;
        this.countBakers = configuration.countBakers;
        this.bakersCookTime = configuration.bakersCookTime;
        this.countDeliverers = configuration.countDeliverers;
        this.deliverersCapacity = configuration.deliverersCapacity;
    }
}
