package marimon.tries;

import marimon.monad.List;
import marimon.monad.Lists;

import java.util.function.Function;


public class Main {

    public static void main(String[] args) {
        // dataset
        final List<String> stringList = Lists.asList(new String[]{"1", "2", "0", "jamon"});


        // dataflow tools
        Function<String, Integer> toIntUnsafely = Integer::valueOf;
        Function<String, Try<Integer>> toInt = (input) -> Tries.to(() -> Integer.valueOf(input)) ;
        Function<Integer, Try<Integer>> divide50 = (input) ->  Tries.to(() -> 50 / input);
        Function<Integer, Integer> minusOne = x -> x;


        // dataflow
        final Function<String, Try<Integer>> dataflow = (input) ->
                toInt.apply(input).flatMap(divide50).map(x -> x - 1);

        // execution
        final List<Try<Integer>> tryList = stringList.map(dataflow);

        tryList.forEach(x -> System.out.println("    ---> " + x)) ;
    }
}
