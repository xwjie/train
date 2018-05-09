package flowdemo;

import java.util.Arrays;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class FluxDemoSimple {

	public static void main(String[] args) throws InterruptedException {
		// Create Publisher
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

		// Create Subscriber
		Subscriber<String> subscriber = new Subscriber<String>() {

			private Subscription subscription;

			@Override
			public void onSubscribe(Subscription subscription) {
				this.subscription = subscription;
				this.subscription.request(1);
			}

			@Override
			public void onNext(String data) {
				System.out.println("处理一个数据：" + data);
				this.subscription.request(1);
			}

			@Override
			public void onError(Throwable throwable) {
				throwable.printStackTrace();

				this.subscription.cancel();
			}

			@Override
			public void onComplete() {
				this.subscription.cancel();
			}
		};

		// Subscriber
		publisher.subscribe(subscriber);

		System.out.println("Publishing Items...");

		String[] items = { "1", "x", "2", "x", "3", "x" };

		Arrays.asList(items).stream().forEach(i -> publisher.submit(i));

		publisher.close();

		synchronized (FluxDemoSimple.class) {
			FluxDemoSimple.class.wait();
		}
	}
}
