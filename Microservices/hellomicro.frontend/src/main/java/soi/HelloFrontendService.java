package soi;

import hellomicro.HelloGrpc;
import hellomicro.HelloGrpc.HelloBlockingStub;
import hellomicro.HelloOuterClass.SayHelloReply;
import hellomicro.HelloOuterClass.SayHelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloFrontendService extends HelloGrpc.HelloImplBase {
	@GrpcClient("helloBackend")
	private HelloBlockingStub backend;

	@Override
	public void sayHello(SayHelloRequest request, StreamObserver<SayHelloReply> responseObserver) {
		var replyFromBackend = backend.sayHello(request);
		var message = "Hello from frontend: " + replyFromBackend.getMessage();
		var reply = SayHelloReply.newBuilder().setMessage(message).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}