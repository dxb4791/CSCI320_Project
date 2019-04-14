package View;

import db.Dealer;
import db.DealerTable;
import db.H2DatabaseMain;

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
            System.out.println("Commands:\n-[L]ist dealers\n\tSort by sales" +
                    "\n-[F]ind Dealer\n\t-Car by VIN\n\t-Car by CLASS");
            String input = in.next();
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'L':
                    System.out.println("This will list all dealers");

                    Connection conn = demo.getConnection();
                    DealerTable.printDealersBySales(conn);
                    break;
                case 'F':
                    System.out.println("This will find dealers for cars by VIN or CLASS");
                    break;
                case 'Q':
                    running = false;
                    continue;
                default:
                    System.out.println("Invalid character");
                    break;
            }
        }
    }
}
