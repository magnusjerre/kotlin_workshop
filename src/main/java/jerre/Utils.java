package jerre;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Utils {
    /**
     * formula: P = [r * (PV)] / [1 - (1 + r)^(-n)]
     * P = Payment
     * PV = present value
     * r = rate per period
     * n = number of periods
     *
     * http://financeformulas.net/Annuity_Payment_Formula.html
     * @param n
     * @return
     */
    public static BigDecimal annuity(BigDecimal pv, int n, BigDecimal r) {
        return (r.multiply(pv)).divide(BigDecimal.ONE.subtract(BigDecimal.ONE.add(r).pow(-n, MathContext.DECIMAL128)), RoundingMode.HALF_UP);
    }

    public static void accessStaticMethodsFromKotlinCode() {
        System.out.println(Beer.General.getMinimunAlcoholStrength());
    }
}
