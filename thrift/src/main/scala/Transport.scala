//
//import org.apache.thrift.protocol.{ TBinaryProtocol, TProtocol }
//import org.apache.thrift.transport.{ TMemoryBuffer, TMemoryInputTransport }
//import org.jboss.netty.buffer.{ ChannelBuffer, ChannelBuffers }
//
//object Transport {
//  val protocolFactory = new TBinaryProtocol.Factory
//
//  protected final def toChannelBuffer(t: TObject): ChannelBuffer = {
//    //TMemoryBuffer allocation begins at 32 and grows based on need.
//    val buf = new TMemoryBuffer(32)
//    val oprot = protocolFactory.getProtocol(buf)
//    TObject.encode(t, oprot)
//    ChannelBuffers.copiedBuffer(Arrays.copyOfRange(buf.getArray, 0, buf.length))
//  }
//
//  protected final def fromChannelBuffer(buffer: ChannelBuffer): TObject = {
//    val buf = new TMemoryInputTransport(buffer.array())
//    val oprot = protocolFactory.getProtocol(buf)
//    TObject.decode(oprot)
//  }
//}

