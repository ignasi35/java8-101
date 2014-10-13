package marimon.whiteboard;

public class MultiparamMethodReference {

  private String x;
  private Integer i;
  private Integer j;


  public MultiparamMethodReference(String x, Integer i, Integer j) {
    this.x = x;
    this.i = i;
    this.j = j;
  }

  public void f(Foo1 f) {
    final Character c = f.f(x, i);
    System.out.println("it is " + c);
  }

  public void f2(Foo2 f) {
    final Integer c = f.f(x, j, i);
    System.out.println("it is " + c);
  }

  public static void main(String[] args) {
    // This invocation demonstrates how hello and '2' are the target and the param of 'String::charAt' method reference
    final MultiparamMethodReference target =
      new MultiparamMethodReference("Hello World", 4, (int) 'l');
    target.f(String::charAt);

    target.f2(String::indexOf);

  }

}

@FunctionalInterface
interface Foo1 {
  Character f(String x, Integer i);
}

@FunctionalInterface
interface Foo2 {
  Integer f(String x, Integer i, Integer j);
}