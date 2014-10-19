package marimon.whiteboard;

/**
 * This code sample demonstrates how we can use method reference over methods
 * on the API that receive several arguments.
 */
public class MultiparamMethodReference {

  private String x;
  private Integer i;
  private Integer j;


  public MultiparamMethodReference(String x, Integer i, Integer j) {
    this.x = x;
    this.i = i;
    this.j = j;
  }

  public void f1proxy(Foo1 f1) {
    final Character c = f1.apply1(x, i);
    System.out.println("it is " + c);
  }

  public void f2proxy(Foo2 f2) {
    final Integer c = f2.apply2(x, j, i);
    System.out.println("it is " + c);
  }

  public static void main(String[] args) {
    // This invocation demonstrates how hello and '2' are the target and the
    // param of 'String::charAt' method reference
    final MultiparamMethodReference target =
      new MultiparamMethodReference("Hello World", 4, (int) 'l');

    target.f1proxy((s, i) -> s.charAt(i));
    target.f1proxy(String::charAt);

    /** f2proxy requires (String, Integer, Integer) -> Integer, but we can pass a method
     * reference to String::indexOf(int, int) because it returns int and the compiler
     * knows the first argument will be the object over which indexOf will be invoked
     * with the other two arguments as arguments.
     */
    target.f2proxy((s, i, j) -> s.indexOf(i, j));
    target.f2proxy(String::indexOf);

  }

}

@FunctionalInterface
interface Foo1 {
  Character apply1(String x, Integer i);
}

@FunctionalInterface
interface Foo2 {
  Integer apply2(String x, Integer i, Integer j);
}