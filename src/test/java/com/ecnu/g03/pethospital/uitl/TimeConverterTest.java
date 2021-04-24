package com.ecnu.g03.pethospital.uitl;

import com.ecnu.g03.pethospital.util.TimeConverter;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/24 11:19
 */
@SpringBootTest
public class TimeConverterTest {

    @ParameterizedTest
    @MethodSource("provideDB2UISource")
    public void testDBTimeToUI(String dbTime, String uiTime) {
        String generated = TimeConverter.DBTimeToUI(dbTime);
        Assert.assertEquals(uiTime, generated);
    }

    static List<Arguments> provideDB2UISource() {
        return Arrays.asList(
                Arguments.of("2021-04-23T15:20:52.064Z", "2021-04-23 23:20:52"),
                Arguments.of("2000-01-01T00:00:00.000Z", "2000-01-01 08:00:00"));
    }

}
