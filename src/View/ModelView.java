package View;

import java.util.Scanner;

public class ModelView implements View{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Command:\n-List All:\n-Sort:\n\tbrand" +
                "\n\tclass\n\tproduced\n-Produce Car");
    }
}
