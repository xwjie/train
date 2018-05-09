package webflux.handlers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import webflux.domain.User;
import webflux.repository.UserRepository;

@Component
public class UserHandler {

	private final UserRepository repository;

	public UserHandler(UserRepository rep) {
		this.repository = rep;
	}

	/**
	 * 得到所有用户
	 * 
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> getAllUser(ServerRequest request) {
		return ok().contentType(APPLICATION_JSON_UTF8)
				.body(this.repository.findAll(), User.class);
	}

	/**
	 * 创建用户
	 * 
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> createUser(ServerRequest request) {
		// 2.0.0 是可以工作, 但是2.0.1 下面这个模式是会报异常
		Mono<User> user = request.bodyToMono(User.class);

		return user.flatMap(u -> {	
			return ok().contentType(APPLICATION_JSON_UTF8)
					.body(this.repository.save(u), User.class);
		});
	}

	/**
	 * 根据id删除用户
	 * 
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> deleteUserById(ServerRequest request) {
		String id = request.pathVariable("id");

		return this.repository.findById(id)
				.flatMap(
						user -> this.repository.delete(user).then(ok().build()))
				.switchIfEmpty(notFound().build());
	}

}
