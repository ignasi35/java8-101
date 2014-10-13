package marimon.whiteboard;


import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FizzBuzzMain {

  public static void main(String[] args) {

    IntStream ints = IntStream.iterate(1,(x)-> x+1);

    Stream<String> fizzbuzzStream = ints.mapToObj(Integer::toString);

    fizzbuzzStream.limit(100).forEach(System.out::println);

  }
}
