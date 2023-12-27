package study;

public class StringCalculator {

    public int calculate(String expression) throws Exception {
        String[] values = expression.split(" ");
        validateExpression(values);
        int[] numbers = convertToNumbers(values);
        String[] symbols = convertToSymbols(values);

        int result = numbers[0];
        for(int index = 0; index < symbols.length; index++){
            if(symbols[index].equals("+"))
                result = sum(result, numbers[index + 1]);
            if(symbols[index].equals("-"))
                result = difference(result, numbers[index + 1]);
            if(symbols[index].equals("*"))
                result = product(result, numbers[index + 1]);
            if(symbols[index].equals("/"))
                result = quotient(result, numbers[index + 1]);
        }
        return result;
    }

    void validateExpression(String[] values) throws Exception {
        if(values.length % 2 != 1){
            throw new Exception("unavailable Expression");
        }
    }

    int sum(int numberFirst, int numberSecond){
        return numberFirst + numberSecond;
    }

    int difference(int numberFirst, int numberSecond){
        return numberFirst - numberSecond;
    }

    int product(int numberFirst, int numberSecond){
        return numberFirst * numberSecond;
    }

    int quotient(int numberFirst, int numberSecond){
        return numberFirst / numberSecond;
    }
    int[] convertToNumbers(String[] values){
        int valuesLength = values.length;
        int numberCount = (valuesLength + 1) / 2;
        int[] numbers = new int[numberCount];

        for(int index = 0; index < numberCount; index++){
            numbers[index] = Integer.parseInt(values[index * 2]);
        }
        return numbers;
    }

    String[] convertToSymbols(String[] values){
        int valuesLength = values.length;
        int symbolCount = (valuesLength - 1) / 2;
        String[] symbols = new String[symbolCount];
        for(int index = 0; index < symbolCount; index++){
            symbols[index] = values[(index * 2) + 1];
        }
        return symbols;
    }

}