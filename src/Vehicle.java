/**
 * Vehicle attribute implementation
 * @author dxb4791
 */
public class Vehicle {
    private final String vin;
    private final String mileage;
    private final String D_ID;
    private final String options;
    private final String price;
    private final String ModelName;

    /**
     * regular constructor
     * @param vin
     * @param mileage
     * @param D_ID dealer id
     * @param options
     * @param price
     * @param ModelName
     */
    public Vehicle(String vin,
            String mileage,
            String D_ID,
            String options,
            String price,
            String ModelName){
        this.vin=vin;
        this.mileage=mileage;
        this.D_ID=D_ID;
        this.options=options;
        this.price=price;
        this.ModelName=ModelName;

    }

    /**
     * csv constructor
     * @param data
     */
    public Vehicle(String[] data){
        this.vin =data[0];
        this.mileage =data[1];
        this.D_ID =data[2];
        this.options =data[3];
        this.price =data[4];
        this.ModelName =data[5];

    }

    public String getD_ID() {
        return D_ID;
    }

    public String getMileage() {
        return mileage;
    }

    public String getModelName() {
        return ModelName;
    }

    public String getOptions() {
        return options;
    }

    public String getPrice() {
        return price;
    }

    public String getVin() {
        return vin;
    }
}
