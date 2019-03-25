package View;

import java.util.Scanner;

public class ModelView implements View{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Command:\n-[L]ist All:\n-[S]ort:\n\tbrand\n\tclass\n\tproduced\n-[P]roduce Car");
        String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'L':
                System.out.println("This will list all models");
                break;
            case 'S':
                System.out.println("This will sort models by brand,class, or produced");
                break;
            case 'P':
                System.out.println("This will produce a car");
                break;
            default:
                return;
        }
    }
}
