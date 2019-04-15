package View;

import db.CustomerTable;
import db.DealerTable;
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
        System.out.println("Buy Page\n-[S]ales Analytics:\n-[M]anage Inventory\n");
        String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'S':
                System.out.println("List Customers By:\n-[I]ncome\n");
                String temp = in.nextLine();
                switch(temp.charAt(0)){
                    case 'I':
                        CustomerTable.printCustomersByIncome(demo.getConnection());
                        break;
                    default:
                        DealerTable.findDealer(demo.getConnection(),Integer.parseInt(temp));
                        break;
                }
                break;
            case 'M':
                System.out.println("-[A]dd car\n-[I]ncome\n");
                temp = in.nextLine();
                switch(temp.charAt(0)){
                    case 'A':
                        System.out.println("This will add a make by values(m_id)");
                        System.out.println("Please Enter a M_ID");

                        String p_string = in.nextLine();
                        String[]  parsed = p_string.split(" ");
                        VehicleTable.addCar(demo.getConnection(),parsed[0],Integer.parseInt(parsed[1]),Integer.parseInt(parsed[2]),parsed[3],Integer.parseInt(parsed[4]),parsed[5]);
                        break;
                    case 'I':
                        CustomerTable.printCustomersByIncome(demo.getConnection());
                        break;
                    default:
                        DealerTable.findDealer(demo.getConnection(),Integer.parseInt(temp));
                        break;
                }
                break;
            case 'Q':
                return;
            default:
                return;
        }
    }
}
