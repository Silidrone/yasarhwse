/*
Student Number: 21070006208
Name Surname: Muhamed Cicak
 */

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GrayCodeTest {
    private final GrayCode grayCode = new GrayCode();

    //t1 and t6
    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    void testNullOutput(int n) {
        assertNull(grayCode.grayCodeSequence(n));
    }

    //t2 and t3
    @ParameterizedTest
    @CsvSource({
            "1,         '0, 1'",
            "2,        '00, 01, 11, 10'",
    })
    void testSmallValidInputs(int n, @ConvertWith(StringToStringArrayConverter.class) String[] output) {
        assertArrayEquals(output, grayCode.grayCodeSequence(n));
    }

    //t4 and t5
    @ParameterizedTest
    @CsvFileSource(resources = "/t4output.csv", maxCharsPerColumn=200000)
    void testBigValidInputs(int n, @ConvertWith(StringToStringArrayConverter.class) String[] output) {
        assertArrayEquals(output, grayCode.grayCodeSequence(n));
    }
}