import java.nio.file.{Files, Paths}

import com.twitter.chill.{Input, Output, ScalaKryoInstantiator}
import org.scalatest.{FlatSpec, Matchers}

class KryoPersonSerializerTest extends FlatSpec with Matchers {

  "The kryo serialization " should
    "serialize and de-serialize our person " in {


    class Person(val id: Int, val firstname: String,val lastname: String)
    val data = new Person(1, "Christian", "Uhl")
    val fileName = "person_kryo.serialized"
    // setup
    // setup
    val instantiator = new ScalaKryoInstantiator
    instantiator.setRegistrationRequired(false)
    val kryo = instantiator.newKryo()

    // write

    val buffer = new Array[Byte](16)
    val output = new Output(buffer)
    kryo.writeObject(output, data)
    Files.write(Paths.get(fileName),buffer)

    val readByteArray = Files.readAllBytes(Paths.get(fileName))

    // read
    val input = new Input(readByteArray)
    val data2 = kryo.readObject(input,classOf[Person])
    data2.asInstanceOf[Person].firstname shouldBe "Christian"

  }
}
