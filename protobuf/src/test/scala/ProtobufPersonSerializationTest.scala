import java.io.{File, FileInputStream, FileOutputStream}

import de.christianuhl.proto.Person.Person
import org.scalatest.{FlatSpec, Matchers}

class ProtobufPersonSerializationTest extends FlatSpec with Matchers {

  "The protobuf serialization " should
    "serialize and de-serialize our person " in {

      val fileName = "person_proto_new.serialized"

      // given a Person
      val originalPerson = Person()
        .withFirstname("Christian")
        .withLastname("Uhl")
        .withFavoriteConference("scalaExchange")

      // when written to a file
      val file = new File(fileName)
      val outputStream = new FileOutputStream(file)
      originalPerson.writeTo(outputStream)
      outputStream.close
      val input = new FileInputStream(file)
      val readPerson = Person.parseFrom(input)

      //then should still be the same thing
      readPerson.firstname shouldBe "Christian"
      readPerson.favoriteConference shouldBe "scalaExchange"
      readPerson shouldEqual originalPerson

    }

    it should "serialize and de-serialize our old person " in {

      // when written to a file
      val file = new File("person_proto.serialized")

      val input = new FileInputStream(file)
      val readPerson = Person.parseFrom(input)

      //then should still be the same thing
      readPerson.firstname shouldBe "Christian"

    }
}
