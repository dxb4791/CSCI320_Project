/**
 * class that handles the model attribute
 * @author dxb4791
 */

public class Model {
    private final String Name;
    private final String year;
    private final String c_class;
    private final String doors;
    private final String seats;
    private final String make_name;

    /**
     * regular Model constructor
     * @param Name name of the model
     * @param year year of the model
     * @param c_class class of the car coupe, van ,..etc.
     * @param seats number of the seats
     * @param doors number of the doors
     * @param makeName name of the make
     */
    public Model(String Name,String year,String c_class,String seats,String doors,String makeName){
        this.Name=Name;
        this.year=year;
        this.c_class=c_class;
        this.seats=seats;
        this.doors=doors;
        this.make_name=makeName;
    }

    /**
     * csv file constructor
     * @param data data from the csv
     */
    public Model(String[] data){
        this.Name = data[0];
        this.year = data[1];
        this.c_class=data[2];
        this.seats=data[3];
        this.doors=data[4];
        this.make_name=data[5];
    }

    /**
     * make name getter
     * @return
     */
    public String getMakename() {
        return make_name;
    }

    /**
     * name getter
     * @return
     */
    public String getName() {
        return Name;
    }

    /**
     * class getter
     * @return
     */
    public String getC_class() {
        return c_class;
    }

    /**
     * doors getter
     * @return
     */
    public String getDoors() {
        return doors;
    }

    public String getMake_name() {
        return make_name;
    }

    public String getSeats() {
        return seats;
    }

    public String getYear() {
        return year;
    }
}
