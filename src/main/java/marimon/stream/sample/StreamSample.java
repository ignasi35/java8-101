package marimon.stream.sample;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamSample {

    public static void process(String name){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println(name);
    }

    public static void main(String... args) {
        Stream.of("Javi", "Ivan", "Facundo", "Fran")
                .flatMap(name -> Arrays.asList(name, name, name).stream())
                .parallel()
                .forEach(StreamSample::process);

    }

}
