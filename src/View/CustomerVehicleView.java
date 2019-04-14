package View;

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
        Scanner in = new Scanner(System.in);
        System.out.println("db.Customer db.Vehicle Page");
        System.out.println("What would you like to do?");
        System.out.println("- [L]ist all owned vehicles");

        while(in.hasNext()) {
            String input = in.nextLine();

            if (input.length() == 0) {
                input = "q";
            }
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'L':
                    System.out.println("This will list all owned vehicles");
                    H2DatabaseMain demo = new H2DatabaseMain();

                    //Hard drive location of the database
                    String location = "./database/database";
                    String user = "ceo";
                    String password = "test";

                    //Create the database connections, basically makes the database
                    demo.createConnection(location, user, password);
                    Connection conn = demo.getConnection();
                    try {
                        Statement statement = conn.createStatement();
                        System.out.println("Enter your name: (This is a wip, only prints for bob johnson)");
                        String name = in.nextLine();
                        String stringquery = "SELECT VIN, NAME FROM CUSTOMER WHERE name = \'" + name + "\';";
                        //String stringquery = "select * from customer where name = 'Bob Johnson';";
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
                default:
                    return;
            }
        }

    }
}
