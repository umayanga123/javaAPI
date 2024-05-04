package soi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import streaming.MovieStreamGrpc.MovieStreamImplBase;
import streaming.MovieStreamOuterClass.GetMoviesReply;
import streaming.MovieStreamOuterClass.GetMoviesRequest;
import streaming.MovieStreamOuterClass.MovieInfo;
import streaming.MovieStreamOuterClass.WatchReply;
import streaming.MovieStreamOuterClass.WatchRequest;
import streaming.SingleMovieGrpc.SingleMovieStub;
import streaming.SingleMovieOuterClass.GetFramesReply;
import streaming.SingleMovieOuterClass.GetFramesRequest;
import streaming.SingleMovieOuterClass.GetLengthReply;
import streaming.SingleMovieOuterClass.GetLengthRequest;
import streaming.SingleMovieOuterClass.GetTitleReply;
import streaming.SingleMovieOuterClass.GetTitleRequest;

@GrpcService
public class MovieStreamService extends MovieStreamImplBase {

	@GrpcClient("movie-1")
	private SingleMovieStub moviePiService;

	@GrpcClient("movie-2")
	private SingleMovieStub movieEService;

	@Override
	public void getMovies(GetMoviesRequest request, StreamObserver<GetMoviesReply> responseObserver) {

		// Create lists to store movie titles and lengths
		List<String> titles = new ArrayList<>();
		List<Integer> lengths = new ArrayList<>();

		// Call MoviePiService to get movie info
		var moviePiRequest = GetTitleRequest.newBuilder().build();
		moviePiService.getTitle(moviePiRequest, new StreamObserver<GetTitleReply>() {
			@Override
			public void onNext(GetTitleReply reply) {
				String title = reply.getTitle();
				titles.add(title);

				// When all titles are collected, get the lengths
				if (titles.size() == 3) {
					// Get lengths for all titles
					getLengths(responseObserver, titles, lengths);
				}
			}

			@Override
			public void onError(Throwable throwable) {
				// Handle errors if any
			}

			@Override
			public void onCompleted() {
				// Handle completion if needed
			}
		});

		// Call MovieEService to get movie info
		var movieERequest = GetTitleRequest.newBuilder().build();
		movieEService.getTitle(movieERequest, new StreamObserver<GetTitleReply>() {
			@Override
			public void onNext(GetTitleReply reply) {
				String title = reply.getTitle();
				titles.add(title);

				// When all titles are collected, get the lengths
				if (titles.size() == 3) {
					// Get lengths for all titles
					getLengths(responseObserver, titles, lengths);
				}
			}

			@Override
			public void onError(Throwable throwable) {
				// Handle errors if any
			}

			@Override
			public void onCompleted() {
				// Handle completion if needed
			}
		});

		// defualt moview
		titles.add("sqrt2");
		lengths.add(1500);
	}

	// Helper method to get lengths for all titles
	private void getLengths(StreamObserver<GetMoviesReply> responseObserver, List<String> titles,
			List<Integer> lengths) {
		for (String title : titles) {
			var movieLengthRequest = GetLengthRequest.newBuilder().build();
			StreamObserver<GetLengthReply> observer = new StreamObserver<GetLengthReply>() {
				@Override
				public void onNext(GetLengthReply reply) {
					int length = reply.getLength();
					lengths.add(length);

					// When all lengths are collected, construct the reply
					if (lengths.size() == 3) {
						// Construct GetMoviesReply
						GetMoviesReply movieList = constructAndGetMoviesReply(titles, lengths);

						// Send the reply to the client
						responseObserver.onNext(movieList);
						responseObserver.onCompleted();
					}
				}

				@Override
				public void onError(Throwable throwable) {
					// Handle errors if any
				}

				@Override
				public void onCompleted() {
					// Handle completion if needed
				}
			};

			// Get length for the current title
			if ("pi".equals(title)) {
				moviePiService.getLength(movieLengthRequest, observer);
			} else if ("e".equals(title)) {
				movieEService.getLength(movieLengthRequest, observer);
			}
		}
	}

	// Helper method to construct GetMoviesReply
	private GetMoviesReply constructAndGetMoviesReply(List<String> titles, List<Integer> lengths) {
		GetMoviesReply.Builder builder = GetMoviesReply.newBuilder();
		for (int i = 0; i < titles.size(); i++) {
			builder.addMovies(MovieInfo.newBuilder().setTitle(titles.get(i)).setLength(lengths.get(i)).build());
		}
		return builder.build();
	}

	@Override
	public void watch(WatchRequest request, StreamObserver<WatchReply> responseObserver) {
		String title = request.getTitle();
		int startPosition = request.getStartPosition();

		// System.out.println("Watch request for title: " + title + ", starting at
		// position: " + startPosition);

		if ("pi".equals(title)) {
			// Call MoviePiService to get movie frames
			var movieFramePiRequest = GetFramesRequest.newBuilder().build();
			moviePiService.getFrames(movieFramePiRequest, new StreamObserver<GetFramesReply>() {
				int currentPosition = startPosition;

				@Override
				public void onNext(GetFramesReply value) {
					// Send frames starting from the specified position
					List<Integer> framesToSend = value.getFrameList().subList(currentPosition,
							value.getFrameList().size());
					for (int digit : framesToSend) {
						var frameReply = WatchReply.newBuilder().setFrame(digit).build();
						responseObserver.onNext(frameReply);
					}
					// Update the current position
					currentPosition = value.getFrameList().size();
				}

				@Override
				public void onError(Throwable t) {
					// Handle error
				}

				@Override
				public void onCompleted() {
					// Send completion signal to the client
					responseObserver.onCompleted();
				}
			});
		} else if ("e".equals(title)) {
			// Call MovieEService to get movie frames
			var movieFrameERequest = GetFramesRequest.newBuilder().build();
			movieEService.getFrames(movieFrameERequest, new StreamObserver<GetFramesReply>() {
				int currentPosition = startPosition;

				@Override
				public void onNext(GetFramesReply value) {
					// Send frames starting from the specified position
					List<Integer> framesToSend = value.getFrameList().subList(currentPosition,
							value.getFrameList().size());
					for (int digit : framesToSend) {
						var frameReply = WatchReply.newBuilder().setFrame(digit).build();
						responseObserver.onNext(frameReply);
					}
					// Update the current position
					currentPosition = value.getFrameList().size();
				}

				@Override
				public void onError(Throwable t) {
					// Handle error
				}

				@Override
				public void onCompleted() {
					// Send completion signal to the client
					responseObserver.onCompleted();
				}
			});
		} else if ("sqrt2".equals(title)) {
			// Call MoviePiService to get movie frames
			int currentPosition = startPosition;
			String result = "141421356237309504880168872420969807856967187537694807317667973799073247846210703885038753432764157273501384623091229702492483605585073721264412149709993583141322266592750559275579995050115278206057147010955997160597027453459686201472851741864088919860955232923048430871432145083976260362799525140798968725339654633180882964062061525835239505474575028775996172983557522033753185701135437460340849884716038689997069900481503054402779031645424782306849293691862158057846311159666871301301561856898723723528850926486124949771542183342042856860601468247207714358548741556570696776537202264854470158588016207584749226572260020855844665214583988939443709265918003113882464681570826301005948587040031864803421948972782906410450726368813137398552561173220402450912277002269411275736272804957381089675040183698683684507257993647290607629969413804756548237289971803268024744206292691248590521810044598421505911202494413417285314781058036033710773091828693147101711116839165817268894197587165821521282295184884720896946338628915628827659526351405422676532396946175112916024087155101351504553812875600526314680171274026539694702403055174953188629256313851881634780015693691768818523786840522878376293892143006558695686859645951555016447245098368960368873231143894155766510408839142923381132060524336294853170499157717562285497414389991880217624309652065642118273167262575395947172559346372386322614827426222086711558395999265211762526989175409881593486400834570851814722318142040704265090565323333984364578657967";

			// Convert the result string to a character array
			char[] resultChars = result.toCharArray();
			// Check if the current position is within the range of the result string
			if (currentPosition >= 0 && currentPosition < resultChars.length) {
				// Create a sublist starting from the current position
				char[] subResultChars = Arrays.copyOfRange(resultChars, currentPosition,
						Math.min(1500, resultChars.length));
				// Iterate over the sublist and send frame replies
				for (char digitChar : subResultChars) {
					int digit = Character.getNumericValue(digitChar);
					var frameReply = WatchReply.newBuilder().setFrame(digit).build();
					responseObserver.onNext(frameReply);
				}

			}

			// Send completion signal to the client
			responseObserver.onCompleted();

		} else {
			// Handle other titles here if needed
		}

	}

	public static String calculateSqrt2(int precision) {
		StringBuilder result = new StringBuilder("1."); // Initialize the result with "1."

		// Initialize variables
		long remainder = 0;
		long dividend = 0;
		long divisor = 0;
		long quotient = 0;

		for (int i = 0; i < precision; i++) {
			// Multiply remainder by 100
			remainder *= 100;

			// Compute the next decimal digit
			dividend = (remainder * 20) + 10;
			divisor = quotient * (20 * i + 10);
			quotient = dividend / divisor;
			remainder = dividend % divisor;

			// Append the digit to the result
			result.append(quotient);
		}

		return result.toString();
	}
}
