package marimon.partial;

import marimon.monad.Option;
import marimon.monad.Options;
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

    @Test
    public void anApplicablePartialFuncReturnsTheValueWhenGetIsCalled() {
        Option<Integer> result = new Fraction().get(4);
        Assert.assertEquals(10, result.get().intValue());
    }

    @Test
    public void aNonApplicablePartialFuncReturnsNoneWhenGetIsCalled() {
        Option<Integer> result = new Fraction().get(0);
        Assert.assertEquals(Options.<Integer>none(), result);
    }

    @Test
    public void aNonApplicablePartialFuncReturnsDefaultValueWhenGetOrElseIsCalled() {
        Integer result = new Fraction().getOrElse(0, 23);
        Assert.assertEquals(23, result.intValue());
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