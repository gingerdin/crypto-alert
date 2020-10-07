package dl.invest.calc;

import java.math.BigDecimal;

/**
 * @author dlevchuk
 * @since 1.0
 **/
public class ComplexPercentage {


    /**
     * A = P * pow ((1 + r/n), n * t)
     * A - total sum
     * P - initial capital
     * r - period percentage
     * n - how many times during the period the percentage is being applied
     * t - number of periods
     *
     * @return
     */
    public static double complexPercent(double P, double r, int n, int t) {
        return P * Math.pow((1 + r/n), n * t);
    }


    public static void main(String[] arg) {
        System.out.println(complexPercent(10000, 0.12, 1, 5));
    }
}
