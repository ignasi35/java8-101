package marimon.monad;

import javax.swing.*;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ignasi on 22/04/14.
 */
public interface List<T> {
    <R> List<R> flatMap(Function<? super T, ? extends List<? extends R>> f);

    <R> List<R> map(Function<? super T, ? extends R> f);

    T head();

    List<T> tail();
}

class Nil<T> implements List<T> {

    @Override
    public <R> List<R> flatMap(Function<? super T, ? extends List<? extends R>> f) {
        return (List<R>) this;
    }

    @Override
    public <R> List<R> map(Function<? super T, ? extends R> f) {
        return (List<R>) this;
    }

    @Override
    public T head() {
        throw new NoSuchElementException();
    }

    @Override
    public List<T> tail() {
        throw new NoSuchElementException();
    }
}

class Const<T> implements List<T> {

    private java.util.List<T> content;

    Const(java.util.List<T> content) {this.content = content;}

    @Override
    public <R> List<R> flatMap(Function<? super T, ? extends List<? extends R>> f) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <R> List<R> map(Function<? super T, ? extends R> f) {
        java.util.List<R> rs = content.stream().map(f).collect(Collectors.toList());
        return new Const(rs);
    }

    @Override
    public T head() {return content.get(0);}

    @Override
    public List<T> tail() {
        if(content.size()>1) return new Const(content.subList(1, content.size()));
        else return new Nil<>();
    }
}