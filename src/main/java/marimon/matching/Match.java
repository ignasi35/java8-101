package marimon.matching;

import marimon.monad.List;
import marimon.monad.Lists;
import marimon.partial.PartialFunction;

import java.util.Arrays;
import java.util.Iterator;


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