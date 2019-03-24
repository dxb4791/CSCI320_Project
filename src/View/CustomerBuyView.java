package View;

import java.util.Scanner;

/**
 * author: apg4944
 */

public class CustomerBuyView implements View  {

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Customer Buy Page");
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
