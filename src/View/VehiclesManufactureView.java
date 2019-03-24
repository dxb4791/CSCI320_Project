package View;

import java.util.Scanner;

public class VehiclesManufactureView implements View{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Modify:\n[B]rand\\Make\n[M]odel\n");
        String input = in.next();
        char prefix = input.charAt(0);
        prefix = Character.toUpperCase(prefix);
        switch (prefix){
            case 'B':
                BrandView bv = new BrandView();
                bv.run();
                break;
            case 'M':
                ModelView mv = new ModelView();
                mv.run();
                break;
            case 'Q':
                return;
            default:
                return;
        }
    }
}
