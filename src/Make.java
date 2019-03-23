/**
 * Created by ceo on 3/23/2019.
 */
public class Make {
    String makename;
    String model;

    public Make(String make, String model){
        this.makename = make;
        this.model = model;
    }

    public Make(String[] data){
        this.makename = data[0];
        this.model = data[1];
    }

    public String getMakename() {
        return makename;
    }

    public String getModel() {
        return model;
    }
}
