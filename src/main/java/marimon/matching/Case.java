package marimon.matching;

import marimon.partial.PartialFunction;

import java.util.function.Function;

/**
 * Created by ignasi on 22/04/14.
 */
public class Case<T> {

    private Function<T, Boolean> predicate;

    public static <T1> Case<T1> definedAt(Function<T1, Boolean> predicate){
        return new Case(predicate);
    }

    private Case(Function<T, Boolean> predicate){
        this.predicate = predicate;
    }

    public <R> PartialFunction<T, R> apply(Function<T, R> f){
        return new PartialFunction<T, R>(){

            @Override
            public boolean isDefinedAt(T t) {
                return predicate.apply(t);
            }

            @Override
            public R apply(T t) {
                return f.apply(t);
            }
        };
    }
}
