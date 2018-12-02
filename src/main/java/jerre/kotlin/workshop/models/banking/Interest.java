package jerre.kotlin.workshop.models.banking;

import jerre.kotlin.workshop.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interest {
    private final BigDecimal rate;

    /**
     * pattern: d.dd%
     * @param rate
     */
    public Interest(String rate) {
        Pattern pattern = Pattern.compile("^(\\d+\\.\\d\\d)\\s?%$");
        Matcher matcher = pattern.matcher(rate);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Wrong percent format");
        }
        BigDecimal inputValue = BigDecimal.valueOf(Double.parseDouble(matcher.group(1)))
                .divide(BigDecimal.valueOf(100L), 4, RoundingMode.HALF_UP);
        this.rate = Utils.currencyScale(inputValue);
    }

    public BigDecimal getRate() {
        return rate;
    }
}
