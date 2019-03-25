package View;

import java.util.Scanner;

public class DealerBuyView implements View{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Buy Page\n-[L]ist all:\n\tmodels,brands,price" +
                "\n-[B]uy vin\n");
        String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'L':
                System.out.println("This will list all models,brands,price");
                break;
            case 'B':
                System.out.println("This will buy vin");
                break;
            case 'Q':
                return;
            default:
                return;
        }
    }
}
