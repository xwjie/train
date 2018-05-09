package flowdemo2;

import java.util.Arrays;
import java.util.concurrent.SubmissionPublisher;

public class FluxDemo1 {

	public static void main(String[] args) throws InterruptedException {
		// Create Publisher
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

		// Create Processor and Subscriber
		MyFilterProcessor<String> filterProcessor = new MyFilterProcessor<>(s -> s.equals("x"));

		MyTransformProcessor<String, Integer> transformProcessor = new MyTransformProcessor<>(s -> Integer.parseInt(s));

		MySubscriber<Integer> subscriber = new MySubscriber<>();

		// Chain Processor and Subscriber
		publisher.subscribe(filterProcessor);

		filterProcessor.subscribe(transformProcessor);

		transformProcessor.subscribe(subscriber);

		System.out.println("Publishing Items...");

		String[] items = { "1", "x", "2", "x", "3", "x" };

		Arrays.asList(items).stream().forEach(i -> publisher.submit(i));

		publisher.close();

		synchronized (FluxDemo1.class) {
			FluxDemo1.class.wait();
		}
	}
}
