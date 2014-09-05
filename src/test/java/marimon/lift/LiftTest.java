package marimon.lift;

import marimon.partial.PartialFunction;
import org.junit.Assert;
import marimon.monad.Option;
import marimon.monad.Options;
import org.junit.Test;

import java.util.function.Function;

public class LiftTest {

    private final Function<String, String> toUpperCase = String::toUpperCase;


    // Option param/return lifters
    @Test(expected = NullPointerException.class)
    public void testANonLiftedFunctionInvokedWithNullThrowsNPE() {
        toUpperCase.apply(null);
    }

    @Test
    public void testALiftedFunctionInvokedWithNullDoesntThrowNPE() {
        final Function<Option<String>, Option<String>> lifted = Lifters.lift1(toUpperCase);
        final Option<String> result = lifted.apply(Options.none());
        Assert.assertEquals(Options.<String>none(), result);
    }

    private final PartialFunction<Integer, Integer> fraction = new Fraction();

    // PF to F lifters
    @Test
    public void anApplicablePFisLiftedToFAndReturnsSome() {
        final Function<Integer, Option<Integer>> lifted = Lifters.lift1(fraction);
        Assert.assertEquals(Options.some(1), lifted.apply(42));
    }

    @Test
    public void aNonApplicablePFisLiftedToFAndReturnsNone() {
        final Function<Integer, Option<Integer>> lifted = Lifters.lift1(fraction);
        Assert.assertEquals(Options.<Integer>none(), lifted.apply(0));
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