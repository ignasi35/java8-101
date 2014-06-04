package marimon.lift;

import org.junit.Assert;
import marimon.monad.Option;
import marimon.monad.Options;
import org.junit.Test;

import java.util.function.Function;

public class LiftTest {

    Function<String, String> toUpperCase = (String s) -> {
        return s.toUpperCase();
    };


    @Test(expected = NullPointerException.class)
    public void testANonLiftedFunctionInvokedWithNullThrowsNPE() {

        toUpperCase.apply(null);

    }

    @Test
    public void testALiftedFunctionInvokedWithNullDoesntThrowNPE() {

        Option<String> result = Lifters.lift1(toUpperCase).apply(Options.none());
        Assert.assertEquals(Options.none(), result);

    }


}
