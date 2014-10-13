package marimon.lang;

import marimon.monad.Option;
import marimon.tries.Try;

import java.util.function.Function;

public class For {

    public static <A, B> Option<B> comprehension(Option<A> input,
                                                 Function<A, B> f1) {
        return input
                .map((A a) -> f1.apply(a));
    }

    public static <A, B, C> Option<C> comprehension(Option<A> input,
                                                    Function<A, Option<B>> f1,
                                                    Function<B, C> f2) {
        return input
                .flatMap((a) -> f1.apply(a))
                .map((b) -> f2.apply(b));
    }

    public static <A, B, C> Try<C> comprehension(Try<A> input,
                                                    Function<A, Try<B>> f1,
                                                    Function<B, C> f2) {
        return input
                .flatMap((a) -> f1.apply(a))
                .map((b) -> f2.apply(b));
    }

    public static <A, B, C, D> Option<D> comprehension(Option<A> input,
                                                       Function<A, Option<B>> f1,
                                                       Function<B, Option<C>> f2,
                                                       Function<C, D> f3) {
        return input
                .flatMap((A a) -> f1.apply(a))
                .flatMap((B b) -> f2.apply(b))
                .map((C c) -> f3.apply(c));
    }

    public static <A, B, C, D, E> Option<E> comprehension(Option<A> input,
                                                          Function<A, Option<B>> f1,
                                                          Function<B, Option<C>> f2,
                                                          Function<C, Option<D>> f3,
                                                          Function<D, E> f4) {
        return input
                .flatMap((A a) -> f1.apply(a))
                .flatMap((B b) -> f2.apply(b))
                .flatMap((C c) -> f3.apply(c))
                .map((D d) -> f4.apply(d));
    }

}
