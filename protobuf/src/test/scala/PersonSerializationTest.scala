import java.io.{File, FileInputStream, FileOutputStream}

import de.christianuhl.proto.Person.Person
import org.scalatest.{FlatSpec, Matchers}

class PersonSerializationTest extends FlatSpec with Matchers {

  val fileName = "person_proto.serialized"

  "The protobuf serialization " should
    "serialize and de-serialize our person " in {

    // given a Person
    val originalPerson = Person().withFirstname("Christian").withLastname("Uhl")

    // when written to a file
    val file = new File(fileName)
    val outputStream = new FileOutputStream(file)
    originalPerson.writeTo(outputStream)
    outputStream.close
    val input = new FileInputStream(file)
    val readPerson = Person.parseFrom(input)

    //then should be the same
    readPerson.firstname shouldBe "Christian"
    readPerson shouldEqual originalPerson

  }

}
