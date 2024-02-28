package fr.charly.chatApp.service;

import fr.charly.chatApp.DTO.RegisterPostDTO;
import fr.charly.chatApp.entity.Category;
import fr.charly.chatApp.entity.Chatter;
import fr.charly.chatApp.entity.User;
import fr.charly.chatApp.entity.Moderator;
import fr.charly.chatApp.repository.UserRepository;
import fr.charly.chatApp.service.interfaces.DAOFindByIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService implements DAOFindByIdInterface<User>, UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }





    public Chatter create(RegisterPostDTO registerPostDTO) {
        Chatter gamer = new Chatter();
        gamer.setNickname(registerPostDTO.getNickname());
        gamer.setEmail(registerPostDTO.getEmail());
        gamer.setPassword(passwordEncoder.encode(registerPostDTO.getPassword()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        gamer.setBirthAt(LocalDate.parse(registerPostDTO.getBirthAt(), formatter));
        return userRepository.saveAndFlush(gamer);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByNickname(username);

        return new org.springframework.security.core.userdetails.User(
                user.getNickname(),
                user.getPassword(),
                userGrantedAuthority(user)
        );

    }


    private List<GrantedAuthority> userGrantedAuthority (User user) {
        if (user instanceof Moderator) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_CHATTER"));
    }

}
