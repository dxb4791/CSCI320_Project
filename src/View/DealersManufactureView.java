package View;

import db.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class DealersManufactureView implements View{
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
        boolean running = true;
        while (running) {
            System.out.println("Commands:\n-[L]ist dealers\n\t" +
                    "\n-[F]ind All Dealers\n\t-[V]Car by VIN\n\t-[A]dd dealer\n\t-[R]emove Dealer");
            String input = in.next();
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'L':
                    System.out.println("This will list all dealers");

                    Connection conn = demo.getConnection();
                    DealerTable.printDealerTable(conn);
                    break;

                case 'F':
                    System.out.println("This will find dealers for cars by VIN or CLASS");
                    break;
                case 'V':
                    System.out.println("This will find a dealer by VIN");
                    System.out.println("Please Enter a VIN");

                    try {
                        String d_id = VehicleTable.findVehicle(demo.getConnection(), in.nextLine()).getString(2);
                        DealerTable.findDealer(demo.getConnection(), Integer.parseInt(d_id));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'A':
                    System.out.println("This will add a dealer");
                    System.out.println("Please Enter dealer values[D_ID name location inventory primarymake]");

                    String p_string = in.nextLine();
                    String[] parsed = p_string.split(" ");
                    DealerTable.addDealer(demo.getConnection(), new Dealer(parsed[0], parsed[1], parsed[2], parsed[3], parsed[4]));

                    break;
                case 'R':
                    System.out.println("This will remove a dealer by D-ID");
                    System.out.println("Please Enter a D_ID");

                    String d_id = in.nextLine();
                    DealerTable.removeDealer(demo.getConnection(), d_id);
                    break;

                case 'Q':
                    running = false;
                    continue;
                default:
                    break;
            }
        }
    }
}
