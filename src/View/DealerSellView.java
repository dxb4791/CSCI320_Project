package View;

import java.util.Scanner;

public class DealerSellView implements View {
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Sell Page\n\t-[S]ell to Customer" +
                "\n\t-[L]ist all customers by: income, name\n");
        String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'S':
                System.out.println("This will sell to customer");
                break;
            case 'L':
                System.out.println("This will list all customers");
                break;
            case 'Q':
                return;
            default:
                return;
        }
    }
}
