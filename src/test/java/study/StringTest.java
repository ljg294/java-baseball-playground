package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    @DisplayName("\"1,2\"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.\n" +
            "\"1\"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.")
    void splitNumber() {
        String numberString1 = "1,2";
        String[] actual1 = numberString1.split(",");
        assertThat(actual1).contains("1");
        assertThat(actual1).contains("2");

        String numberString2 = "1";
        String[] actual2 = numberString2.split(",");
        assertThat(actual2).containsExactly("1");
    }

    @Test
    @DisplayName("\"(1,2)\" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 \"1,2\"를 반환하도록 구현한다.")
    void subStringNumber() {
        String numberString = "(1,2)";
        String actual = numberString.substring(1, 4);
        assertThat(actual).isEqualTo("1,2");
    }

    @Test
    @DisplayName("\"abc\" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.")
    void charAt() {
        String string = "abc";

        assertThat(string.charAt(0)).isEqualTo('a');
        assertThat(string.charAt(1)).isEqualTo('b');
        assertThat(string.charAt(2)).isEqualTo('c');

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> {
                    string.charAt(3);
                }).withMessageMatching("String index out of range: 3");

    }
}
