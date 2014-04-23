package marimon.partial;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ignasi on 22/04/14.
 */
public class PartialFunctionTest {

    @Test
    public void anApplicablePartialFuncIsApplied() {
        Integer result = new Fraction().apply(4);
        Assert.assertEquals(10, result.intValue());
    }

    @Test(expected = ArithmeticException.class)
    public void aNonApplicablePartialFuncIsNotApplied() {
        new Fraction().apply(0);
    }

}

class Fraction implements PartialFunction<Integer, Integer> {

    @Override
    public boolean isDefinedAt(Integer t) {
        return t != 0;
    }

    @Override
    public Integer apply(Integer t) {
        return 42 / t;
    }
}