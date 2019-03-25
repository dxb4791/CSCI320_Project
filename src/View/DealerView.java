package View;

import java.util.Scanner;

public class DealerView implements View{

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello Dealer!\n\nWhat would you like to do?" +
                "\n[s]ell a car\n[b]uy a car\n");
        String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'S':
                DealerSellView dsv = new DealerSellView();
                dsv.run();
                break;
            case 'B':
                DealerBuyView dbv = new DealerBuyView();
                dbv.run();
                break;
            case 'Q':
                return;
            default:
                return;
        }
    }
}
