package de.christianuhl.protobufsample;


import de.christianuhl.proto.PersonOuterClass.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SampleController {

    @PostMapping(value = "/person")
    String parseIncoming(@RequestBody byte[] rawBody) throws IOException {
        Person person = Person.parseFrom(rawBody);
        return "Hi " + person.getFirstname() + "you're at " + person.getFavoriteConference();
    }

    @GetMapping(value = "/person")
    Person out() {
        return Person.newBuilder().setFirstname("Christian").setLastname("Uhl").build();
    }
}
