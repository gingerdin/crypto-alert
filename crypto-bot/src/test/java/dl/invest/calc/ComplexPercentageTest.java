package dl.invest.calc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author dlevchuk
 * @since 1.0
 **/
public class ComplexPercentageTest {

    @Test
    public void test() {
        assertEquals(17623.416832, ComplexPercentage.complexPercent(10000, 0.12, 1, 5), 0.0000001);
    }
}
