package marimon.lang;

import marimon.domain.Address;
import marimon.domain.City;
import marimon.domain.Credentials;
import marimon.domain.Person;
import marimon.monad.Option;
import marimon.monad.Options;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static junit.framework.Assert.assertEquals;

public class ForTest {

    private Credentials incompleteCredentials;
    private Credentials credentials;

    @Before
    public void before() {
        incompleteCredentials =
                new Credentials(new Person(new Address())
                );
        credentials =
                new Credentials(new Person(new Address(
                        new City("Gran de Gràcia")))
                );
    }

    @Test(expected = NullPointerException.class)
    public void whenFieldIsMissingNPEIsThrown() {
        String streetName = incompleteCredentials.
                person.get().
                address.get().
                city.get().
                street;
    }

    public Function<Credentials, Option<String>> streetName =
            (credentials) -> For.comprehension(
                    credentials.person,
                    (Person p) -> p.address,
                    (Address a) -> a.city,
                    (City c) -> c.street
            );

    @Test
    public void whenFieldIsMissingNoneIsReturned() {
        assertEquals(Options.none(), incompleteCredentials.get(streetName));
    }

    @Test
    public void whenModelIsCompleteValueIsReturned() {
        assertEquals(Options.some("Gran de Gràcia"), credentials.get(streetName));
    }

}
