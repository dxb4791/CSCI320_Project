package View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import db.*;

import db.CustomerTable;
import db.H2DatabaseMain;
import db.Vehicle;
import db.VehicleTable;

/**
 * author: apg4944
 */

public class CustomerBuyView implements View  {

    @Override
    public void run() {
        //Hard drive location of the database
        String location = "./database/database";
        String user = "ceo";
        String password = "test";
        H2DatabaseMain demo = new H2DatabaseMain();
        //Create the database connections, basically makes the database
        demo.createConnection(location, user, password);
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("db.Customer Buy Page");
            System.out.println("What would you like to do?");
            System.out.println("- [L]ist all vehicles");
            System.out.println("- [B]uy a vehicle");
            System.out.println("- [F]ind a seller");
            System.out.println("- [C]ar finder");
            String input = in.nextLine();

            if (input.length() == 0) {
                input = "q";
            }
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'L':

                    System.out.println("This will list all vehicles");


                    //Hard drive location of the database

                    //Create the database connections, basically makes the database
                    demo.createConnection(location, user, password);
                    ArrayList<String> cols = new ArrayList<>(Arrays.asList(""));
                    VehicleTable.printUnpurchasedCars(demo.getConnection());
                    break;
                case 'B':
                    System.out.println("This will buy a vehicle");
                    System.out.println("Please enter a VIN:");

                    VehicleTable.updateAfterBuy(demo.getConnection(),in.nextLine());
                    break;
                case 'F':
                    System.out.println("This will find a seller");
                    System.out.println("Please Enter your c_id");
                    Customer cust= CustomerTable.findUser(demo.getConnection(),in.nextLine());
                    if(cust.getD_ID()==null){
                        System.out.println("Please Enter a d_id or enter 'a' to see all dealers");
                        String temp = in.nextLine();
                        switch(temp.charAt(0)){
                            case 'a':
                                DealerTable.printDealerTable(demo.getConnection());
                            default:
                                DealerTable.findDealer(demo.getConnection(),Integer.parseInt(temp));
                        }
                    }
                    else{
                        DealerTable.findDealer(demo.getConnection(),Integer.parseInt(cust.getD_ID()));
                    }
                    break;

                case 'C':
                    String loc = "./database/database";
                    String use = "ceo";
                    String pass = "test";

                    H2DatabaseMain dem = new H2DatabaseMain();
                    //Create the database connections, basically makes the database
                    dem.createConnection(loc, use, pass);
                    CustomerTable.findCar(dem.getConnection());

                case 'Q':
                    running = false;
                    continue;
                default:
                    System.out.println("Invalid Command");
                    break;
            }

        }
    }



}
