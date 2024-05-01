package soi;

import hellomicro.HelloGrpc;
import hellomicro.HelloOuterClass.SayHelloReply;
import hellomicro.HelloOuterClass.SayHelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;



@GrpcService
public class HelloBackendService extends HelloGrpc.HelloImplBase {

	@Override
	public void sayHello(SayHelloRequest request, StreamObserver<SayHelloReply> responseObserver) {
		var name = request.getName();
		var message = "Hello from backend: " + name;
		var reply = SayHelloReply.newBuilder().setMessage(message).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}