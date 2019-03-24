/**
 * Class for the dealer
 * @author dxb4791
 */
public class Dealer {
    private final String D_ID;
    private final String name;
    private final String location;
    private final String inventory;
    private final String primaryMake;

    /**
     *
     * @param D_ID dealer id
     * @param name name
     * @param location address of the dealer
     * @param inventory inventory
     * @param primaryMake brand the dealer sells
     */
    public Dealer(String D_ID,
            String name,
            String location,
            String inventory,
            String primaryMake){
        this.D_ID=D_ID;
        this.name=name;
        this.location=location;
        this.inventory=inventory;
        this.primaryMake=primaryMake;
    }

    /**
     * csv file constructor
     * @param data
     */
    public Dealer(String[] data){
        this.D_ID=data[0];
        this.name=data[1];
        this.location=data[2];
        this.inventory=data[3];
        this.primaryMake=data[4];
    }

    /**
     * dealer id getter
     * @return
     */
    public String getD_ID() {
        return D_ID;
    }

    /**
     * inventory getter
     * @return
     */
    public String getInventory() {
        return inventory;
    }

    /**
     * location getter
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * name getter
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * primary make getter
     * @return
     */
    public String getPrimaryMake() {
        return primaryMake;
    }
}
