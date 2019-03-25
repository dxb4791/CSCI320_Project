package View;

import java.util.Scanner;

public class DealersManufactureView implements View{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Commands:\n-List dealers\n\tSort by sales" +
                "\n-Find Dealer\n\t-Car by VIN\n\t-Car by CLASS");
    }
}
