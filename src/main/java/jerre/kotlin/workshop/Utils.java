package jerre.kotlin.workshop;

import jerre.kotlin.workshop.models.Id;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

public class Utils {
    public static Long getNextIdInSequence(Collection<? extends Id> ids) {
        return ids.stream().map(id -> id.getId()).max(Long::compare).orElse(1L);
    }

    public static BigDecimal currencyScale(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
