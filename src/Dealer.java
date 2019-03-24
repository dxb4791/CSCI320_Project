public class Dealer {
    private final String D_ID;
    private final String name;
    private final String location;
    private final String inventory;
    private final String primaryMake;

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
    public Dealer(String[] data){
        this.D_ID=data[0];
        this.name=data[1];
        this.location=data[2];
        this.inventory=data[3];
        this.primaryMake=data[4];
    }

    public String getD_ID() {
        return D_ID;
    }

    public String getInventory() {
        return inventory;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPrimaryMake() {
        return primaryMake;
    }
}
