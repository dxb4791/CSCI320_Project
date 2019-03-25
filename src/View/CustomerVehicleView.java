package View;

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

        String input = in.next();

        if(input.length() == 0){
            input = "q";
        }
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'L':
                System.out.println("This will list all owned vehicles");
                break;
            default:
                return;
        }

    }
}
