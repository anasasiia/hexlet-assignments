package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findUserById(long id) {
        return userRepository.findById((int) id);
    }

    public Mono<User> updateUser(long id, User user) {
        return userRepository.findById((int) id)
                .switchIfEmpty(Mono.error(new Exception("USER_NOT_FOUND")))
                .map(u -> {
                    user.setId(id);
                    return user;
                })
                .flatMap(userRepository::save);
    }

    public Mono<Void> deleteUser(long id) {
        return userRepository.deleteById((int) id);
    }
    // END
}
