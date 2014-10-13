package marimon.whiteboard;


import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamRevisitingMain {

  public static void main(String[] args) throws IOException {
    final Stream<Integer> stream =
      Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).parallelStream();

    final List<Integer> c1 = stream.limit(2).collect(Collectors.toList());
    c1.stream().forEach(System.out::println);

    final List<Integer> c2 = stream.limit(2).collect(Collectors.toList());
    c2.stream().forEach(System.out::println);


    Files.lines(null);
    final IntStream randomInts = new Random().ints();

  }
}
