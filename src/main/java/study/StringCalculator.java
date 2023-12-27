package study;

public class StringCalculator {

    public int calculate(String expression) throws Exception {
        String[] values = expression.split(" ");
        if(!isExpressionAvailable(values)){
            throw new Exception("unavailable Expression");
        }
        int[] numbers = convertToNumbers(values);
        String[] symbols = convertToSymbols(values);

        int result = numbers[0];
        for(int index = 0; index < symbols.length; index++){
            switch(symbols[index]){
                case "+":
                    result = sum(result, numbers[index + 1]);
                    break;
                case "-":
                    result = difference(result, numbers[index + 1]);
                    break;
                case "*":
                    result = product(result, numbers[index + 1]);
                    break;
                case "/":
                    result = quotient(result, numbers[index + 1]);
                    break;
                default:
                    throw new Exception("unavailable symbol");
            }
        }
        return result;
    }

    private boolean isExpressionAvailable(String[] values){
        return values.length % 2 == 1;
    }

    private int sum(int numberFirst, int numberSecond){
        return numberFirst + numberSecond;
    }

    private int difference(int numberFirst, int numberSecond){
        return numberFirst - numberSecond;
    }

    private int product(int numberFirst, int numberSecond){
        return numberFirst * numberSecond;
    }

    private int quotient(int numberFirst, int numberSecond){
        return numberFirst / numberSecond;
    }
    private int[] convertToNumbers(String[] values){
        int valuesLength = values.length;
        int numberCount = (valuesLength + 1) / 2;
        int[] numbers = new int[numberCount];

        for(int index = 0; index < numberCount; index++){
            numbers[index] = Integer.parseInt(values[index * 2]);
        }
        return numbers;
    }

    private String[] convertToSymbols(String[] values){
        int valuesLength = values.length;
        int symbolCount = (valuesLength - 1) / 2;
        String[] symbols = new String[symbolCount];
        for(int index = 0; index < symbolCount; index++){
            symbols[index] = values[(index * 2) + 1];
        }
        return symbols;
    }

}