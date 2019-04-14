package db;

/**
 * Created by ceo and dxb4791 on 3/24/2019.
 *
 */
public class Customer {
    private final String C_ID;
    private final String name;
    private final String address;
    private final String phone;
    private final String gender;
    private final int income;
    private final String vin;
    private final String D_ID;
    public Customer(String C_ID,
                         String name,
                         String address,
                         String phone,
                         String gender,
                         int income,
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

    /**
     *
     * @param data split form csv
     */
    public Customer(String[] data){
        this.C_ID=data[0];
        this.name=data[1];
        this.address=data[2];
        this.phone = data[3];
        this.gender=data[4];
        this.income=Integer.parseInt(data[5]);
        this.vin=data[6];
        this.D_ID = data[7];
    }

    /**
     * getter for name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * gettern for dealer id
     * @return
     */
    public String getD_ID() {
        return D_ID;
    }

    /**
     * getter for the vin
     * @return
     */
    public String getVin() {
        return vin;
    }

    /**
     * getter for the address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * getter for the customer id
     * @return
     */
    public String getC_ID() {
        return C_ID;
    }

    /**
     * getter for the gender
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * getter for income
     * @return
     */
    public int getIncome() {
        return income;
    }

    /**
     * getter for phone
     * @return
     */
    public String getPhone() {
        return phone;
    }
}
