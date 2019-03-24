/**
 * Created by ceo on 3/23/2019.
 */
public class Make {
    String makename;
    String model;

    /**
     * regular make constructor
     * @param make make or brand of the car
     * @param model model of the car
     */
    public Make(String make, String model){
        this.makename = make;
        this.model = model;
    }

    public Make(String[] data){
        this.makename = data[0];
        this.model = data[1];
    }

    /**
     * name getter
     * @return
     */
    public String getMakename() {
        return makename;
    }

    public String getModel() {
        return model;
    }
}
