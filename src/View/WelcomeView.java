package View;


import java.util.Scanner;

/**
 * Created by ceo on 3/24/2019.
 */
public class WelcomeView implements View {
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while(running) {
            System.out.println("Welcome to: ");
            System.out.println("AUTO MANAGER 2K19 ");
            System.out.println("Please enter your role: ");
            System.out.println("[M]anufacture Employee");
            System.out.println("[D]ealer");
            System.out.println("[C]ustomer");
            System.out.println("Enter the title or [] Prefix of your role (or q to quit):");

            String input = in.next();
            if (input.length() == 0) {
                input = "Q";
            }
            char prefix = input.charAt(0);
            prefix = Character.toUpperCase(prefix);
            switch (prefix) {
                case 'M':
                    System.out.println("Manufacture employee");
                    ManufactureView mv = new ManufactureView();
                    mv.run();
                    break;
                case 'D':
                    System.out.println("Dealer employee");
                    DealerView dv = new DealerView();
                    dv.run();
                    break;
                case 'C':
                    System.out.println("Customer");
                    CustomerView cv = new CustomerView();
                    cv.run();
                    break;
                case 'Q':
                    System.out.println("Quit application");
                    running = false;
                    continue;
                default:
                    System.out.println("Invalid prefix entered");
            }
        }
    }

    public static void main(String[] args) {
        WelcomeView wv = new WelcomeView();
        wv.run();
    }
}
