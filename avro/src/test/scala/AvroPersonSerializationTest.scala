import java.io.File

import com.sksamuel.avro4s.{AvroInputStream, AvroOutputStream}
import de.christianuhl.avro.Person
import org.scalatest.{FlatSpec, Matchers}

class AvroPersonSerializationTest extends FlatSpec with Matchers {

  "The avro serialization " should
    "serialize and de-serialize our person " in {

    val fileName = "person_avro_new.serialized"

    // given a Person
    val originalPerson = Person(None, "Christian", "Uhl")


    // when written to a file
    val os = AvroOutputStream.data[Person](new File("person_avro.serialized"))
    os.write(originalPerson)
    os.flush()
    os.close()

    val is = AvroInputStream.data[Person](new File("person_avro.serialized"))
    val readPerson = is.iterator.toSet.head
    is.close()
    //then should still be the same thing\
    readPerson.firstname shouldBe "Christian"
    readPerson shouldEqual originalPerson

  }
}
