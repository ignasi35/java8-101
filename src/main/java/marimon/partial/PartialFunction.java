package marimon.partial;

import marimon.monad.Option;

import java.util.function.Function;

public interface PartialFunction<T, R> extends Function<T, R> {

    boolean isDefinedAt(T t);

    default OrElse<T, R> orElse(PartialFunction<? super T, ? extends R> pf) {
        return new OrElse<>(this, pf);
    }

    @Override
    default <Q> AndThen<T,R, Q> andThen(Function<? super R, ? extends Q> f2){
        return new AndThen<>(this , f2);
    }

}

class AndThen<T, R, Q> implements PartialFunction<T, Q>{

    private PartialFunction<? super T, ? extends R> pf1;
    private Function<? super R, ? extends Q> f2;

    AndThen(PartialFunction<? super T, ? extends R> pf1, Function<? super R, ? extends Q> f2){
        this.pf1 = pf1;
        this.f2 = f2;
    }

    @Override
    public boolean isDefinedAt(T t) {
        return pf1.isDefinedAt(t);
    }

    @Override
    public Q apply(T t) {
        if(pf1.isDefinedAt(t)) return  f2.apply(pf1.apply(t));
        else throw new MatchError();
    }
}

class OrElse<T, R> implements PartialFunction<T, R>{
    private PartialFunction<? super T, ? extends R> pf1;
    private PartialFunction<? super T, ? extends R> pf2;

    OrElse(PartialFunction<? super T, ? extends R> pf1, PartialFunction<? super T, ? extends R> pf2) {
        this.pf1 = pf1;
        this.pf2 = pf2;
    }

    @Override
    public R apply(T t) {
        if (pf1.isDefinedAt(t)) return pf1.apply(t);
        else if(pf2.isDefinedAt(t)) return pf2.apply(t);
        else throw new MatchError() ;
    }

    @Override
    public boolean isDefinedAt(T t) {
        return pf1.isDefinedAt(t) || pf2.isDefinedAt(t);
    }
}

class MatchError extends Error {
}