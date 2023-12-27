package study;

import java.util.Scanner;

public class StringCalculate {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.calculate(scanner.nextLine());
        System.out.println("result : " + result);
    }
}
