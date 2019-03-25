package View;
import java.util.Scanner;
import db.H2DatabaseMain;
import db.Vehicle;
import db.VehicleTable;

/**
 * author: apg4944
 */

public class CustomerBuyView implements View  {

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("db.Customer Buy Page");
        System.out.println("What would you like to do?");
        System.out.println("- [L]ist all vehicles");
        System.out.println("- [B]uy a vehicle");
        System.out.println("- [F]ind a seller");

        String input = in.next();

        if(input.length() == 0){
            input = "q";
        }
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'L':

                System.out.println("This will list all vehicles");
                H2DatabaseMain demo = new H2DatabaseMain();

                //Hard drive location of the database
                String location = "./database/database";
                String user = "ceo";
                String password = "test";

                //Create the database connections, basically makes the database
                demo.createConnection(location, user, password);
                VehicleTable.printVehicleTable(demo.getConnection());
                break;
            case 'B':
                System.out.println("This will buy a vehicle");
                break;
            case 'F':
                System.out.println("This will find a seller");
                break;
            default:
                return;
        }


    }



}
