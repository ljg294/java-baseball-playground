package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    void convertToMathematicalExpression(String expression) throws Exception {
        String[] values = expression.split(" ");
        int valuesLength = values.length;
        int numberCount = (valuesLength + 1) / 2;
        int symbolCount = (valuesLength - 1) / 2;

        int[] numbers = new int[numberCount];
        for(int index = 0; index < numberCount; index++){
            numbers[index] = Integer.parseInt(values[index * 2]);
        }

        String[] symbols = new String[symbolCount];
        for(int index = 0; index < symbolCount; index++){
            symbols[index] = values[(index * 2) + 1];
        }

        int result = numbers[0];
        for(int index = 0; index < symbolCount; index++){
            System.out.println(index);
            System.out.println(symbols[index]);
            switch(symbols[index]){
                case "+":
                    result = result + numbers[index + 1];
                    break;
                case "-":
                    result = result - numbers[index + 1];
                    break;
                case "*":
                    result = result * numbers[index + 1];
                    break;
                case "/":
                    result = result / numbers[index + 1];
                    break;
                default:
                    throw new Exception("unavailable symbol");
            }
        }
        assertThat(result).isEqualTo(10);
    }

    @ParameterizedTest
    @CsvSource(value = {"2 + 3 * 4 / 2, true", "2 + 3 * 4 /, false"})
    @DisplayName("수식 유효성 검사")
    void isExpressionAvailable(String expression, boolean availableYn){
        String[] values = expression.split(" ");
        assertThat(values.length % 2 == 1).isEqualTo(availableYn);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,2", "2,3,5"})
    @DisplayName("덧셈")
    void sum(int numberFirst, int numberSecond, int result){
        assertThat(numberFirst + numberSecond).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"2,1,1", "2,3,-1"})
    @DisplayName("뺄셈")
    void difference(int numberFirst, int numberSecond, int result){
        assertThat(numberFirst - numberSecond).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,1", "2,3,6"})
    @DisplayName("곱셈")
    void product(int numberFirst, int numberSecond, int result){
        assertThat(numberFirst * numberSecond).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,1", "0,3,0"})
    @DisplayName("나눗셈")
    void quotient(int numberFirst, int numberSecond, int result){
        assertThat(numberFirst / numberSecond).isEqualTo(result);
    }
    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    @DisplayName("문자열에서 숫자만 추출하기")
    void convertToNumbers(String expression){
        String[] values = expression.split(" ");
        int valuesLength = values.length;
        int numberCount = (valuesLength + 1) / 2;
        int[] numbers = new int[numberCount];

        for(int index = 0; index < numberCount; index++){
            numbers[index] = Integer.parseInt(values[index * 2]);
        }
        assertThat(numbers).containsExactly(2, 3, 4, 2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    @DisplayName("문자열에서 기호만 추출하기")
    void convertToSymbols(String expression){
        String[] values = expression.split(" ");
        int valuesLength = values.length;
        int symbolCount = (valuesLength - 1) / 2;
        String[] symbols = new String[symbolCount];
        for(int index = 0; index < symbolCount; index++){
            symbols[index] = values[(index * 2) + 1];
        }
        assertThat(symbols).containsExactly("+", "*", "/");
    }

    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    void scanExpression(String inputValue) {
        System.setIn(new ByteArrayInputStream(inputValue.getBytes()));
        Scanner scanner = new Scanner(System.in);
        assertThat(scanner.nextLine()).isEqualTo("2 + 3 * 4 / 2");
    }

}