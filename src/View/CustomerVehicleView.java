package View;

import db.CustomerTable;
import db.H2DatabaseMain;
import db.VehicleTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * author: apg4944
 */

public class CustomerVehicleView implements View  {

    @Override
    public void run() {
        H2DatabaseMain demo = new H2DatabaseMain();

        //Hard drive location of the database
        String location = "./database/database";
        String user = "ceo";
        String password = "test";

        //Create the database connections, basically makes the database
        demo.createConnection(location, user, password);
        Connection conn = demo.getConnection();
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running){


            System.out.println("db.Customer db.Vehicle Page");
            System.out.println("What would you like to do?");
            System.out.println("- [L]ist all owned vehicles");
            System.out.println("- [S]ell vehicle");

            String input = in.nextLine();

            if (input.length() == 0) {
                input = "q";
            }
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'L':
                    System.out.println("This will list all owned vehicles");

                    try {
                        Statement statement = conn.createStatement();
                        System.out.println("Enter your name:");
                        String name = in.nextLine();
                        String stringquery = "SELECT VIN, NAME FROM CUSTOMER WHERE name = \'" + name + "\';";
                        ResultSet result = statement.executeQuery(stringquery);

                        while (result.next()) {
                            System.out.printf("VIN %s: %s \n",
                                    result.getString(1),
                                    result.getString(2));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 's':
                    CustomerTable.customerSellsVehicle(demo.getConnection());
                    break;
                default:
                    return;
            }
        }

    }
}
