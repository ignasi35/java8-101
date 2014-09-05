package marimon.lift;


import marimon.monad.Option;
import marimon.partial.PartialFunction;

import java.util.function.Function;

public class Lifters {

    /**
     * Protects both input and output of NPE.
     */
    public static <T, R> Function<Option<T>, Option<R>> lift1(Function<T, R> f) {
        return new Function<Option<T>, Option<R>>() {
            @Override
            public Option<R> apply(Option<T> tOption) {
                return tOption.map(f);
            }
        };
    }

    /**
     * Lifts PartialFunction to Function
     */
    public static <T, R> Function<T, Option<R>> lift1(PartialFunction<T, R> f) {
        return new PartialFunction<T, Option<R>>() {
            @Override
            public boolean isDefinedAt(T t) {
                try {
                    f.apply(t);
                } catch (Throwable e) {
                    return false;
                }
                return true;
            }

            @Override
            public Option<R> apply(T t) {
                return f.get(t);
            }
        };
    }

}
