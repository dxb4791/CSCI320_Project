/**
 * Created by ceo on 3/24/2019.
 */
public class Customer {
    private final String C_ID;
    private final String name;
    private final String address;
    private final String phone;
    private final String gender;
    private final String income;
    private final String vin;
    private final String D_ID;
    public Customer(String C_ID,
                         String name,
                         String address,
                         String phone,
                         String gender,
                         String income,
                         String vin,
                         String D_ID){
        this.C_ID=C_ID;
        this.name=name;
        this.address=address;
        this.phone = phone;
        this.gender=gender;
        this.income=income;
        this.vin=vin;
        this.D_ID = D_ID;

    }
    public Customer(String[] data){
        this.C_ID=data[0];
        this.name=data[1];
        this.address=data[2];
        this.phone = data[3];
        this.gender=data[4];
        this.income=data[5];
        this.vin=data[6];
        this.D_ID = data[7];
    }

    public String getName() {
        return name;
    }

    public String getD_ID() {
        return D_ID;
    }

    public String getVin() {
        return vin;
    }

    public String getAddress() {
        return address;
    }

    public String getC_ID() {
        return C_ID;
    }

    public String getGender() {
        return gender;
    }

    public String getIncome() {
        return income;
    }

    public String getPhone() {
        return phone;
    }
}
