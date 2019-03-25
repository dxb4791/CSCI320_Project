package View;

import db.H2DatabaseMain;
import db.ModelTable;

import java.util.Scanner;

public class ModelView implements View{
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
        System.out.println("Enter Command:\n-[L]ist All:\n-[S]ort:\n\tbrand\n\tclass\n\tproduced\n-[P]roduce Car");
        String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'L':
                System.out.println("This will list all models");
                ModelTable.printModelTable(demo.getConnection());
                break;
            case 'S':
                System.out.println("This will sort models by brand,class, or produced");
                break;
            case 'P':
                System.out.println("This will produce a car");
                break;
            default:
                return;
        }
    }
}
