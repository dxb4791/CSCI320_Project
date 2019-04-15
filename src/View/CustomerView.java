package View;
import java.util.Scanner;

/**
 * author: apg4944
 */

public class CustomerView implements View {

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Welcome db.Customer!");
            System.out.println("Where would you like to go?");
            System.out.println("- [V]ehicle page");
            System.out.println("- [B]uy a vehicle page");

            String input = in.next();

            if (input.length() == 0) {
                input = "q";
            }
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'B':
                    System.out.println("Buy a vehicle");
                    CustomerBuyView cbv = new CustomerBuyView();
                    cbv.run();
                    break;
                case 'V':
                    System.out.println("Vehicle page");
                    CustomerVehicleView cvv = new CustomerVehicleView();
                    cvv.run();
                    break;

                case 'Q':
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Command entered");
            }

        }
    }
}
