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
    private final T input;

    MatchInput(T input) {
        this.input = input;
    }


    public <R> R in(PartialFunction<T, R>... funcs) {

        marimon.monad.List<PartialFunction<T, R>> list = Lists.asList(funcs);

        return new MatchInput<T>(input).in(list);
    }

    private <R> R in(final List<PartialFunction<T, R>> list) {
        if (list.isEmpty()) throw new MatchError();
        else {
            final PartialFunction<T, R> trOrElse = list.head()
                    .<T, R>orElse(new PartialFunction<T, R>() {
                        @Override
                        public boolean isDefinedAt(T t) {
                            return true;
                        }

                        @Override
                        public R apply(T t) {
                            return (R) Match.match(input).in(list.tail());
                        }
                    });
            return trOrElse.apply(input);
        }

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