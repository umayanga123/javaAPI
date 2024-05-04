package soi;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
public class MoviePiService extends SingleMovieImplBase {

	@Override
	public void getTitle(GetTitleRequest request, StreamObserver<GetTitleReply> responseObserver) {
		var name = "pi";
		var reply = GetTitleReply.newBuilder().setTitle(name).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public void getLength(GetLengthRequest request, StreamObserver<GetLengthReply> responseObserver) {
		int length = 1000;
		var reply = GetLengthReply.newBuilder().setLength(length).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public void getFrames(GetFramesRequest request, StreamObserver<GetFramesReply> responseObserver) {

		// Calculate the first 1000 digits of pi
		String piString = "3141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381964428810975665933446128475648233786783165271201909145648566923460348610454326648213393607260249141273724587006606315588174881520920962829254091715364367892590360011330530548820466521384146951941511609433057270365759591953092186117381932611793105118548074462379962749567351885752724891227938183011949129833673362440656643086021394946395224737190702179860943702770539217176293176752384674818467669405132000568127145263560827785771342757789609173637178721468440901224953430146549585371050792279689258923542019956112129021960864034418159813629774771309960518707211349999998372978049951059731732816096318595024459455346908302642522308253344685035261931188171010003137838752886587533208381420617177669147303598253490428755468731159562863882353787593751957781857780532171226806613001927876611195909216420198";

		// String piString = calculatePi(999); // 1001 to include 3 before decimal point
		GetFramesReply.Builder replyBuilder = GetFramesReply.newBuilder();
		for (char digitChar : piString.toCharArray()) {
			int digit = Character.getNumericValue(digitChar);
			replyBuilder.addFrame(digit);
		}
		// replyBuilder.addFrame('0');
		// Build and send the reply
		GetFramesReply reply = replyBuilder.build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	// Calculate the first 1000 digits of pi using Gauss-Legendre algorithm
	public static String calculatePi(int precision) {
		// Set initial values for Gauss-Legendre algorithm
		BigDecimal a = BigDecimal.ONE;
		BigDecimal b = BigDecimal.ONE.divide(sqrt(BigDecimal.valueOf(2), precision), precision, RoundingMode.HALF_UP);
		BigDecimal t = BigDecimal.valueOf(1.0 / 4.0);
		BigDecimal p = BigDecimal.ONE;

		// Iterate to improve precision
		for (int i = 0; i < precision; i++) {
			BigDecimal aNext = a.add(b).divide(BigDecimal.valueOf(2), precision, RoundingMode.HALF_UP);
			BigDecimal bNext = sqrt(a.multiply(b), precision);
			BigDecimal tNext = t.subtract(p.multiply(a.subtract(aNext).pow(2)));
			BigDecimal pNext = p.multiply(BigDecimal.valueOf(2));

			// Update values for next iteration
			a = aNext;
			b = bNext;
			t = tNext;
			p = pNext;
		}

		// Calculate pi value
		BigDecimal pi = a.add(b).pow(2).divide(t.multiply(BigDecimal.valueOf(4)), precision, RoundingMode.HALF_UP);

		return pi.toString().substring(0, precision + 2); // Include 3 before the decimal point
	}

	// Method to compute square root with specified precision
	public static BigDecimal sqrt(BigDecimal x, int scale) {
		BigDecimal sqrt = BigDecimal.valueOf(Math.sqrt(x.doubleValue()));
		return sqrt.setScale(scale, RoundingMode.HALF_UP);
	}

}
