package flowdemo2;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Function;
import java.util.concurrent.SubmissionPublisher;

public class MyFilterProcessor<T> extends SubmissionPublisher<T> implements Processor<T, T> {

	private Function<? super T, Boolean> function;
	private Subscription subscription;

	public MyFilterProcessor(Function<? super T, Boolean> function) {
		super();
		this.function = function;
	}

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNext(T item) {
		// 不符合的往下走
		if (!function.apply(item)) {
			submit(item);
		}

		subscription.request(1);
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;

		subscription.request(1);
	}

}
