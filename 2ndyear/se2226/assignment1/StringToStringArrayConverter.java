/*
Student Number: 21070006208
Name Surname: Muhamed Cicak
 */

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class StringToStringArrayConverter extends SimpleArgumentConverter {
    @Override
    protected String[] convert(Object o, Class<?> aClass) throws ArgumentConversionException {
        if ((o instanceof String)) {
            String[] result = ((String) o).split(",");
            for(int i = 0; i < result.length; i++) {
                result[i] = result[i].trim();
            }
            return result;
        } else if (o == null) {
            return null;
        } else {
            throw new IllegalArgumentException("Can not convert given input");
        }
    }
}