package View;

import java.util.Scanner;

public class DealerView implements View{

    @Override
    public void run() {
        DealerBuyView dealerBuyView = new DealerBuyView();
        dealerBuyView.run();
//        Scanner in = new Scanner(System.in);
//        boolean running = true;
//        while (running) {
//            System.out.println("Hello Dealer!\n\nWhat would you like to do?" +
//                    "\n[s]ell a car\n[D]ealer Managment \n");
//            String input = in.next();
//            char prefix = input.charAt(0);
//            prefix = Character.toUpperCase(prefix);
//            switch (prefix) {
//                case 'D':
//                    DealerBuyView dbv = new DealerBuyView();
//                    dbv.run();
//                    break;
//                case 'Q':
//                    running = false;
//                    continue;
//                default:
//                    System.out.println("Invalid command entered");
//                    break;
//            }
//        }
    }
}
