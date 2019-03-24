package View;

import java.util.Scanner;

public class BrandView implements View{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Command\n-List All\n-Sort by:" +
                "\n\tmodels\n\tvehicles\n\tvalue");
    }
}
