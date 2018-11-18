import java.io.File

import com.sksamuel.avro4s.{AvroInputStream, AvroOutputStream, AvroSchema}
import de.christianuhl.avro.Person
import org.scalatest.{FlatSpec, Matchers}

class AvroPersonSerializationTest extends FlatSpec with Matchers {

  "The avro serialization " should
    "serialize and de-serialize our person " in {

    val fileName = "person_avro_new.serialized"

    // given a Person
    val originalPerson = Person(None, "Christian", "Uhl")


    val schema = AvroSchema[Person]

    // when written to a file
    val os = AvroOutputStream.data[Person].to(new File("person_avro.serialized")).build(schema)

    os.write(originalPerson)
    os.flush()
    os.close()

    val is = AvroInputStream.data[Person].from(new File("person_avro.serialized")).build(schema)
    val readPerson = is.iterator.toSet.head
    is.close()
    //then should still be the same thing\
    readPerson.firstname shouldBe "Christian"
    readPerson shouldEqual originalPerson

  }
}
