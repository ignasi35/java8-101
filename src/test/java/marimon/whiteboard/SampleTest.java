package marimon.whiteboard;


import org.junit.Assert;
import org.junit.Test;

public class SampleTest {

  @Test
  public void nonFunctionalTest() {
    final Sample1 s1 = new Sample1("Marimon");
    s1.toUpperCase();
    Assert.assertEquals("Marimon", s1.getSurname());
  }

  @Test
  public void moarFunctionalTest() {

  }
}

class Sample1 {
  String surname;

  Sample1(String surname) {
    this.surname = surname;
  }

  public String getSurname() {
    return surname;
  }

  public void toUpperCase() {
    this.surname = surname.toUpperCase();
  }
}

class Sample2 {
  Sample2() {
  }
}

class Sample3 {

}