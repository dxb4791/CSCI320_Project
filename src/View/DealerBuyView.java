package View;

import db.H2DatabaseMain;
import db.VehicleTable;

import java.util.Scanner;

public class DealerBuyView implements View{
    @Override
    public void run() {
        H2DatabaseMain demo = new H2DatabaseMain();

        //Hard drive location of the database
        String location = "./database/database";
        String user = "ceo";
        String password = "test";

        //Create the database connections, basically makes the database
        demo.createConnection(location, user, password);
        Scanner in = new Scanner(System.in);
        System.out.println("Buy Page\n-[L]ist all:\n\tmodels,brands,price" +
                "\n-[B]uy vin\n");
        String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'L':
                System.out.println("This will list all models,brands,price");
                VehicleTable.printVehicleTable(demo.getConnection());
                break;
            case 'B':
                System.out.println("This will buy vin");
                break;
            case 'Q':
                return;
            default:
                return;
        }
    }
}
