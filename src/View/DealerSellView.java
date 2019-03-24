package View;

import java.util.Scanner;

public class DealerSellView implements View {
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Sell Page\n\t-Sell to Customer" +
                "\n\t-List all customers by: income, name\n");
        /*String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'S':

                break;
            case 'L':

                break;
            case 'Q':
                return;
            default:
                return;
        }*/
    }
}
