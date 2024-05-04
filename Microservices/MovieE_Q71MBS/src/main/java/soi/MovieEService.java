package soi;

import java.math.BigDecimal;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import streaming.SingleMovieGrpc.SingleMovieImplBase;
import streaming.SingleMovieOuterClass.GetFramesReply;
import streaming.SingleMovieOuterClass.GetFramesRequest;
import streaming.SingleMovieOuterClass.GetLengthReply;
import streaming.SingleMovieOuterClass.GetLengthRequest;
import streaming.SingleMovieOuterClass.GetTitleReply;
import streaming.SingleMovieOuterClass.GetTitleRequest;

@GrpcService
public class MovieEService extends SingleMovieImplBase {

	@Override
	public void getTitle(GetTitleRequest request, StreamObserver<GetTitleReply> responseObserver) {
		var name = "e";
		var reply = GetTitleReply.newBuilder().setTitle(name).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public void getLength(GetLengthRequest request, StreamObserver<GetLengthReply> responseObserver) {
		int length = 500;
		var reply = GetLengthReply.newBuilder().setLength(length).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public void getFrames(GetFramesRequest request, StreamObserver<GetFramesReply> responseObserver) {

		// Calculate the first 500 digits of Euler's number (e)
		// Calculate e with 500 decimal places
		BigDecimal e = calculateEuler(499);

		// Convert e to a string without decimal point
		String eString = e.toString().replace(".", "");

		// Convert each digit of e to integer and add to the reply
		GetFramesReply.Builder replyBuilder = GetFramesReply.newBuilder();
		for (char digitChar : eString.toCharArray()) {
			int digit = Character.getNumericValue(digitChar);
			replyBuilder.addFrame(digit);
		}

		// Build and send the reply
		GetFramesReply reply = replyBuilder.build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	// Function to calculate Euler's number (e) using the specified precision
	@SuppressWarnings("deprecation")
	static BigDecimal calculateEuler(int precision) {
		BigDecimal e = BigDecimal.ONE;
		BigDecimal factorial = BigDecimal.ONE;

		// Iterate over terms in the series expansion of e
		for (int i = 1; i <= precision; i++) {
			// Calculate factorial(i)
			for (int j = 1; j <= i; j++) {
				factorial = factorial.multiply(BigDecimal.valueOf(j));
			}

			// Add 1 / i! to e
			e = e.add(BigDecimal.ONE.divide(factorial, precision, BigDecimal.ROUND_DOWN));

			// Reset factorial for the next iteration
			factorial = BigDecimal.ONE;
		}

		return e;
	}

}
