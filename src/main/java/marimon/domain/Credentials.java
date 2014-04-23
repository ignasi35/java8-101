package marimon.domain;

import marimon.monad.Option;
import marimon.monad.Options;

import java.util.function.Function;

public class Credentials {
    public final Option<Person> person;

    public Credentials(Person p) {
        if (p != null) {
            this.person = Options.some(p);
        } else {
            this.person = Options.none();
        }

    }

    public Credentials(Option<Person> p) {
        this.person = p;
    }

    public <T> Option<T> get(Function<Credentials, Option<T>> f){
        return f.apply(this);
    }
}
