package marimon.monad;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {


  @Test
  public void testFilter() {
    List<Integer> actual = Lists.asList(new Integer[]{1, 2, 3, 4}).filter((x) -> x % 2 == 0);

    assertEquals(2, actual.head().intValue());
    assertEquals(4, actual.tail().head().intValue());

  }

  @Test
  public void testMap() {
    List<String> actual = new Const<String>("Hello", Lists.nil()).map(String::toUpperCase);
    assertEquals("HELLO", actual.head());
  }

  @Test
  public void testReduce() {
    Integer actual = Lists.asList(new Integer[]{20, 300, 4000, 50000}).reduce(8, (acc, x) -> acc + x);
    assertEquals(54328, actual.intValue());
  }

  @Test
  public void testMapList() {
    List<List<String>> actual = Lists
      .of("hello world", "This is sparta", "asdf asdf")
      .map(s -> Lists.of(s.split(" ")));

    List<String> x = Lists.of("hello", "world");
    List<String> y = Lists.of("This", "is", "sparta");
    List<String> z = Lists.of("asdf", "asdf");
    List<List<String>> expected = Lists.of(x, y, z);

    assertEquals(expected, actual);
  }

  @Test
  public void testflatMap() {
    List<String> actual = Lists.asList(new String[]{"hello world", "This is sparta", "asdf asdf"}).flatMap(s -> Lists.of(s.split(" ")));

    List<String> expected = Lists.asList(new String[]{"hello", "world", "This", "is", "sparta", "asdf", "asdf"});

    assertEquals(expected, actual);
  }


}