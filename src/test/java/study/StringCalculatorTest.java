package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

public class StringCalculatorTest {

    StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2 - 5"})
    void calculate(String expression) throws Exception {
        int actual = stringCalculator.calculate(expression);
        assertThat(actual).isEqualTo(5);
    }

    @Test()
    @DisplayName("수식 유효성 검사")
    void isExpressionAvailable() throws Exception {
        //given
        String expression1 = "2 + 3 * 4 / 2";
        String expression2 = "2 + 3 * 4 /";
        String[] values1 = expression1.split(" ");
        String[] values2 = expression2.split(" ");

        stringCalculator.validateExpression(values1);
        assertThatThrownBy(() -> stringCalculator.validateExpression(values2))
                .isInstanceOf(Exception.class)
                .hasMessage("unavailable Expression");
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,2", "2,3,5"})
    @DisplayName("덧셈")
    void sum(int numberFirst, int numberSecond, int result){
        int actual = stringCalculator.sum(numberFirst, numberSecond);
        assertThat(actual).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"2,1,1", "2,3,-1"})
    @DisplayName("뺄셈")
    void difference(int numberFirst, int numberSecond, int result){
        int actual = stringCalculator.difference(numberFirst, numberSecond);
        assertThat(actual).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,1", "2,3,6"})
    @DisplayName("곱셈")
    void product(int numberFirst, int numberSecond, int result){
        int actual = stringCalculator.product(numberFirst, numberSecond);
        assertThat(actual).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,1", "0,3,0"})
    @DisplayName("나눗셈")
    void quotient(int numberFirst, int numberSecond, int result){
        int actual = stringCalculator.quotient(numberFirst, numberSecond);
        assertThat(actual).isEqualTo(result);
    }
    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    @DisplayName("문자열에서 숫자만 추출하기")
    void convertToNumbers(String expression){
        String[] values = expression.split(" ");
        int[] actual = stringCalculator.convertToNumbers(values);
        assertThat(actual).containsExactly(2, 3, 4, 2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    @DisplayName("문자열에서 기호만 추출하기")
    void convertToSymbols(String expression){
        String[] values = expression.split(" ");
        String[] actual = stringCalculator.convertToSymbols(values);
        assertThat(actual).containsExactly("+", "*", "/");
    }

    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    void scanExpression(String inputValue) {
        System.setIn(new ByteArrayInputStream(inputValue.getBytes()));
        Scanner scanner = new Scanner(System.in);
        assertThat(scanner.nextLine()).isEqualTo("2 + 3 * 4 / 2");
    }

}