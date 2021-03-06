package View;

import java.util.Scanner;

/**
 * Created by ceo on 3/24/2019.
 */
public class ManufactureView implements View {
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while(running) {
            System.out.println("Hello Employee\n" +
                    "What would you like to manage?" +
                    "\n[D]ealers\n[V]ehicles\n");
            String input = in.next();
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'D':
                    DealersManufactureView dmv = new DealersManufactureView();
                    dmv.run();
                    break;
                case 'V':
                    VehiclesManufactureView vmv = new VehiclesManufactureView();
                    vmv.run();
                    break;
                case 'Q':
                    running = false;
                    continue;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}
