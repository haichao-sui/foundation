package org.qinarmy.foundation.sequence;

import java.math.BigInteger;
import java.time.LocalDate;

import static org.qinarmy.foundation.util.Assert.assertGeZero;
import static org.qinarmy.foundation.util.TimeUtils.CLOSE_DATE_FORMATTER;


/**
 * @see Snowflake
 * created  on 2018/5/18.
 */
public class BigIntegerSequence {

    private final Snowflake snowflake;

    public BigIntegerSequence(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    /**
     * @param suffixNumber 大于等于 0
     */
    public BigInteger next(long suffixNumber) {
        return new BigInteger(nextAsString(suffixNumber));
    }

    /**
     * @param suffixNumber 大于等于 0
     */
    public String nextAsString(long suffixNumber) {
        assertGeZero(suffixNumber, "后缀数字不能是负数");

        String prefix = LocalDate.now().format(CLOSE_DATE_FORMATTER);
        String sequence = String.valueOf(snowflake.next());
        return prefix + sequence + suffixWithZero(suffixNumber);
    }

    private String suffixWithZero(long number) {

        String str = String.valueOf(number % 10_0000);
        int count = 5 - str.length();
        char[] chars = new char[count];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '0';
        }
        return new String(chars) + str;
    }

    public Snowflake getSnowflake() {
        return snowflake;
    }
}
