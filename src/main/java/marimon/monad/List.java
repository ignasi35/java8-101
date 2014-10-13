package marimon.monad;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;


public interface List<T> {
    <R> List<R> flatMap(Function<? super T, List<R>> f);

    <R> List<R> map(Function<? super T, R> f);

    T head();

    List<T> tail();

    boolean isEmpty();

    void forEach(Consumer<? super T> f);

    List<T> append(List<T> b);

    default List<T> prepend(T t) {
        return new Const<>(t, this);
    }


}

class Nil<T> implements List<T> {

    @Override
    public <R> List<R> flatMap(Function<? super T, List<R>> f) {
        return (List<R>) this;
    }

    @Override
    public <R> List<R> map(Function<? super T, R> f) {
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

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void forEach(Consumer<? super T> f) {
    }

    @Override
    public List<T> append(List<T> b) {
        return b;
    }

}

class Const<T> implements List<T> {

    private T head;
    private List<T> tail;

    Const(T head, List<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public <R> List<R> flatMap(Function<? super T, List<R>> f) {
        return f.apply(head).append(tail.flatMap(f));
    }

    @Override
    public <R> List<R> map(Function<? super T, R> f) {
        return new Const<>(f.apply(head), tail.map(f));
    }

    @Override
    public T head() {
        return this.head;
    }

    @Override
    public List<T> tail() {
        return this.tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void forEach(Consumer<? super T> f) {
        f.accept(head);
        tail.forEach(f);
    }

    @Override
    public List<T> append(List<T> b) {
        return new Const<>(head, tail.append(b));
    }

}