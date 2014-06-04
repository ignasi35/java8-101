package marimon.monad;


public class Options  {
    public static <T> Some<T> some(T t) { return new Some(t); }
    public static <T> None<T> none() { return new None<T>(); }
}