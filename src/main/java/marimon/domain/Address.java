package marimon.domain;


import marimon.monad.Option;
import marimon.monad.Options;

public class Address {
    public final Option<City> city;

    public Address() {
        this.city = Options.none();
    }

    public Address(City city) {
        if (city == null) {
            this.city = Options.none();
        } else {
            this.city = Options.some(city);
        }
    }

    public Address(Option<City> city) {
        this.city = city;
    }
}
