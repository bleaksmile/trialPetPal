package trialpetpal.demo.users.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import trialpetpal.demo.animal.models.Animal;
import trialpetpal.demo.exception.EmailTakenException;
import trialpetpal.demo.oauthSecurity.JwtTokenUtil;
import trialpetpal.demo.users.models.GoogleUser;
import trialpetpal.demo.users.repositories.MainUserRepository;

import java.util.Set;

public class GoogleUserServiceImpl extends ParentUserService<GoogleUser> {

    @Autowired
    private MainUserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(GoogleUser googleUser) {
        userRepository.save(googleUser);
        return jwtTokenUtil.generateToken(googleUser);
    }

    @Override
    public GoogleUser register(GoogleUser googleUser) throws EmailTakenException, UnirestException {
        return null;
    }

    @Override
    public Set<Animal> animalsLikedByUser(GoogleUser googleUser) {
        return null;
    }

    @Override
    public Set<Animal> animalsToAdoptByUser(GoogleUser googleUser) {
        return null;
    }

    @Override
    public void addAnimalToAnimalsLikedByUser(Animal animal, GoogleUser googleUser) {

    }

    @Override
    public void addAnimalToAnimalsToAdoptByUser(Animal animal, GoogleUser googleUser) {

    }

    @Override
    public void addAnimalToAnimalsOwnedByUser(Animal animal, GoogleUser googleUser) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
