import java.nio.file.{Files, Paths}

import de.christianuhl.proto.Person
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.{TMemoryBuffer, TMemoryInputTransport}
import org.scalatest.{FlatSpec, Matchers}

class ThriftPersonSerializationTest extends FlatSpec with Matchers {

  "The thrift serialization " should
    "serialize and de-serialize our person " in {

    val fileName = "person_thrift.serialized"
    // using the Binary Protocol
    val protocolFactory = new TBinaryProtocol.Factory

    // given a Person
    val originalPerson = Person(None, "Christian", "Uhl")

    // Written to Disk and read again
    val buf = new TMemoryBuffer(32)
    val protocol = protocolFactory.getProtocol(buf)
    Person.encode(originalPerson, protocol)

    Files.write(Paths.get(fileName), buf.getArray())

    val byteArray = Files.readAllBytes(Paths.get(fileName))
    val inputBuf = new TMemoryInputTransport(byteArray)
    val in = protocolFactory.getProtocol(inputBuf)
    val readPerson = Person.decode(in)

    //then should still be the same thing
    readPerson.firstname shouldBe "Christian"
    readPerson shouldEqual originalPerson

  }
}
