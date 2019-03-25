package View;

import java.util.Scanner;

public class DealerBuyView implements View{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Buy Page\n-list all:\n\tmodels,brands,price" +
                "\n-buy vin\n");
        /*String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'L':

                break;
            case 'B':

                break;
            case 'Q':
                return;
            default:
                return;
        }*/
    }
}
