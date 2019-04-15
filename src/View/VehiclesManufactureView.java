package View;

import db.Model;

import java.util.Scanner;

public class VehiclesManufactureView implements View{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Modify:\n[B]rand\\Make\n[M]odel\n");
            String input = in.next();
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'B':
                    DealersManufactureView dmv = new DealersManufactureView();
                    dmv.run();
                    break;
                case 'M':
                    ModelView vmv = new ModelView();
                    vmv.run();
                    break;
                case 'Q':
                    running = false;
                    continue;
                default:
                    System.out.println("Invalid command entered");
                    break;
            }
        }
    }
}
