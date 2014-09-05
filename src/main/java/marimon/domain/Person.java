package marimon.domain;


import marimon.monad.Option;
import marimon.monad.Options;

public class Person {
    public final Option<Address> address;

    public Person() {
        address = Options.none();
    }

    public Person(Address address) {
        if (address == null) {
            this.address = Options.none();
        } else {
            this.address = Options.some(address);
        }
    }

    public Option<Address> getAddress() {
        return address;
    }

    public Person(Option<Address> address) {
        this.address = address;
    }
}
