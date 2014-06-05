package marimon.matching;

import marimon.partial.PartialFunction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class MatchTest {

    PartialFunction<Object, Integer> caseDouble =
            Case.definedAt((Object x) -> x instanceof Double)
                    .apply((Object x) -> 8);

    PartialFunction<Object, Integer> caseInteger =
            Case.definedAt((Object x) -> x instanceof Integer)
                    .apply((Object x) -> 4);

    PartialFunction<Object, Integer> caseAlphabetic =
            Case.definedAt((Object x) -> x instanceof String)
                    .apply((Object x) -> ((String) x).length());

    Object input = (Object) "42items";

    @Test
    public void aMatcherCanBeDefinedWithAnInputAndSomeCases() {
        Integer length = Match.match(input).inImperative(caseAlphabetic,
                caseInteger, caseDouble);
        Assert.assertEquals(7, length.intValue());
    }

    @Test
    public void aMatcherCanBeDefinedWithAnInputAndSomeCasesFunctioanlly() {
        Integer length = Match.match(input).in(caseAlphabetic,
                caseInteger, caseDouble);
        Assert.assertEquals(7, length.intValue());
    }

    @Test(expected = MatchError.class)
    public void aMatcherThrowsException() {
        Match.<Object>match(new ArrayList<Integer>())
                .<Integer>in(caseAlphabetic,caseInteger, caseDouble);
    }

}
