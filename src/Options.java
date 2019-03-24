public class Options {
    private final String o_id;
    private final String transmission;
    private final String color;
    private final String engine;
    private final String drive;
    private final String interior;

    public Options(String o_id,String transmission, String color,
                   String engine,String drive,String interior){
        this.o_id=o_id;
        this.transmission=transmission;
        this.color=color;
        this.engine=engine;
        this.drive=drive;
        this.interior=interior;

    }
    public Options(String[] data){
        this.o_id = data[0];
        this.transmission = data[1];
        this.color=data[2];
        this.engine=data[3];
        this.drive=data[4];
        this.interior=data[5];
    }

    public String getColor() {
        return color;
    }

    public String getDrive() {
        return drive;
    }

    public String getEngine() {
        return engine;
    }

    public String getInterior() {
        return interior;
    }

    public String getO_id() {
        return o_id;
    }

    public String getTransmission() {
        return transmission;
    }
}
