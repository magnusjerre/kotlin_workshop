package jerre.kotlin.workshop.testingmodels;

import java.math.BigDecimal;

public class Roughly {

    private final BigDecimal expected;
    private final BigDecimal actual;

    private Roughly(BigDecimal actual, BigDecimal expected) {
        this.actual = actual;
        this.expected = expected;
    }

    public void plusOrMinus(double value) {
        BigDecimal absDiff = expected.subtract(actual).abs();
        BigDecimal maxOffset = BigDecimal.valueOf(value).abs();
        if (maxOffset.subtract(absDiff).compareTo(BigDecimal.ZERO) < 0) {
            throw new AssertionError(String.format(
                    "Values are not roughly the same, expected %s, but got %s.", expected, actual));
        }
    }

    public static Roughly assertThat(BigDecimal actual) {
        return new Roughly(actual, null);
    }

    public Roughly isRoughly(double expected) {
        return new Roughly(this.actual, BigDecimal.valueOf(expected));
    }
}
