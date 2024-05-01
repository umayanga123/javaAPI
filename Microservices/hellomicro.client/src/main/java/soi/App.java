package soi;

import hellomicro.HelloGrpc;
import hellomicro.HelloOuterClass.SayHelloRequest;
import io.grpc.ManagedChannelBuilder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		var channel = ManagedChannelBuilder.forAddress("localhost", 5100).usePlaintext().build();
		var client = HelloGrpc.newBlockingStub(channel);
		var request = SayHelloRequest.newBuilder().setName("me").build();
		var reply = client.sayHello(request);
		System.out.println(reply.getMessage());
		channel.shutdown();
	}
}
