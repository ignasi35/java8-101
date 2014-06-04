package marimon.matching;

import marimon.monad.Lists;
import marimon.partial.PartialFunction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ignasi on 22/04/14.
 */
public class Match {

    public static <T> MatchInput<T> match(T input) {
        return new MatchInput(input);
    }

}

class MatchInput<T> {
    private T input;

    MatchInput(T input) {
        this.input = input;
    }


   /** TODO
    public <R> R in(PartialFunction<T, R>... funcs) {
        return Lists.asList(funcs);
    }
*/
    public <R> R inImperative(PartialFunction<T, R>... funcs) {
        // I'd like to use a dropWhile equivalent but didn't find one.
        boolean success = false;
        Iterator<PartialFunction<T, R>> iterator = Arrays.asList(funcs).iterator();
        while (!success && iterator.hasNext()) {
            PartialFunction<T, R> partialFunction = iterator.next();
            if (partialFunction.isDefinedAt(input)) {
                return partialFunction.apply(input);
            }
        }
        throw new MatchError();
    }


}