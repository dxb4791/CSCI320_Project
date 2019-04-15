package View;

import db.H2DatabaseMain;
import db.Make;
import db.MakeTable;

import db.Model;

import java.util.Scanner;

public class VehiclesManufactureView implements View{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        H2DatabaseMain demo = new H2DatabaseMain();

        boolean running = true;
        while (running) {
            //Hard drive location of the database
            String location = "./database/database";
            String user = "ceo";
            String password = "test";
            System.out.println("Modify:\n[B]rand\\Make\n[M]odel\n[R]emove make\n[A]dd make");
            String input = in.next();
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'B':
                    DealersManufactureView dmv = new DealersManufactureView();
                    dmv.run();
                    break;
                case 'M':
                    VehiclesManufactureView vmv = new VehiclesManufactureView();

                    vmv.run();
                    break;
                case 'R':
                    System.out.println("This will remove a make by M-ID");
                    System.out.println("Please Enter a M_ID");

                    String d_id = in.nextLine();
                    MakeTable.removeMake(demo.getConnection(), d_id);
                    break;
                case 'A':
                    System.out.println("This will add a make by values(m_id)");
                    System.out.println("Please Enter a M_ID");

                    String p_string = in.nextLine();
                    String[] parsed = p_string.split(" ");
                    MakeTable.addMake(demo.getConnection(), new Make(parsed));

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
