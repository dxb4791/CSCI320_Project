public class Model {
    private final String Name;
    private final String year;
    private final String c_class;
    private final String doors;
    private final String seats;
    private final String make_name;
    public Model(String Name,String year,String c_class,String seats,String doors,String makeName){
        this.Name=Name;
        this.year=year;
        this.c_class=c_class;
        this.seats=seats;
        this.doors=doors;
        this.make_name=makeName;
    }
    public Model(String[] data){
        this.Name = data[0];
        this.year = data[1];
        this.c_class=data[2];
        this.seats=data[3];
        this.doors=data[4];
        this.make_name=data[5];
    }

    public String getMakename() {
        return make_name;
    }

    public String getName() {
        return Name;
    }

    public String getC_class() {
        return c_class;
    }

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
