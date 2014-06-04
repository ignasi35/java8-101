package marimon.lift;


import marimon.monad.Option;

import java.util.function.Function;

public class Lifters {

    public static <T,R> Function<Option<T>, Option<R>> lift1(Function<T,R> f){
        return new Function<Option<T>, Option<R>>(){
            @Override
            public Option<R> apply(Option<T> tOption) {
                return tOption.map(f);
            }
        };
    }

}
