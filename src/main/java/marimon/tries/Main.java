package marimon.tries;

import marimon.monad.List;
import marimon.monad.Lists;

import java.util.Arrays;
import java.util.function.Function;


public class Main {

  public static void main(String[] args) {
    final String[] arr = new String[]{"1", "2", "0", "jamon"};
    final List<String> stringList = Lists.asList(arr);

    System.out.println("  WITHOUT TRY ");
    withoutTryA(Arrays.asList(arr));
    withoutTryB(Arrays.asList(arr));
//    System.out.println("  WITH TRY ");
//    withTry(stringList);
  }

  public static void withoutTryA(java.util.List<String> stringList) {
    for (String s : stringList) {
      System.out.println("    --->   " + ((50 / Integer.valueOf(s)) - 1));
    }
  }

  public static void withoutTryB(java.util.List<String> stringList) {
    for (String s : stringList) {
      try {
        int parsed = Integer.valueOf(s);
        try {
          int div = 50 / parsed;
          int x = div - 1;
          System.out.println("    --->   " + x);
        } catch (ArithmeticException ae) {
          // What do we do ?!
        }
      } catch (NumberFormatException nfe) {
        // What do we do ?!
      }
    }
  }

  public static void withTry(List<String> stringList) {
    final Function<String, Try<Integer>> toInt =
      (input) -> Tries.to(() -> Integer.valueOf(input));
    final Function<Integer, Try<Integer>> divide50 =
      (input) -> Tries.to(() -> 50 / input);

    // unused
    final Function<Integer, Integer> minusOne = x -> x - 1;

    // pipe
    final Function<String, Try<Integer>> pipe =
      (input) -> toInt.apply(input).flatMap(divide50).map(x -> x - 1);

    // execution
    final List<Try<Integer>> tryList = stringList.map(pipe);

    tryList.forEach(x -> System.out.println("    ---> " + x));
  }

}
