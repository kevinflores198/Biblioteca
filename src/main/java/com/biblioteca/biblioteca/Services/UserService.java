package com.biblioteca.biblioteca.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import com.biblioteca.biblioteca.Entities.Image;
import com.biblioteca.biblioteca.Entities.User;
import com.biblioteca.biblioteca.Enumerations.Rol;
import com.biblioteca.biblioteca.Exceptions.MyException;
import com.biblioteca.biblioteca.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void signin(MultipartFile archive, String name, String email, String password, String password2)
            throws MyException {

        validate(name, email, password, password2);

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRol(Rol.USER);

        Image image = imageService.save(archive);

        user.setImage(image);

        userRepository.save(user);
    }

    @Transactional
    public void update(MultipartFile archive, String id, String name, String email, String password, String password2)
            throws MyException {

        validate(name, email, password, password2);

        Optional<User> answer = userRepository.findById(id);

        if (answer.isPresent()) {
            User user = answer.get();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setRol(Rol.USER);

            String idImage = null;

            if (user.getImage() != null) {
                idImage = user.getImage().getId();
            }

            Image image = imageService.updatImage(archive, idImage);

            user.setImage(image);

            userRepository.save(user);
        }

    }

    public void validate(String name, String email, String password, String password2) throws MyException {
        if (name.isEmpty() || name == null) {
            throw new MyException("Name cannot be empty or null.");
        }
        if (email.isEmpty() || email == null) {
            throw new MyException("Email cannot be empty or null.");
        }
        if (password.isEmpty() || password == null) {
            throw new MyException("Passrowd cannot be empty or null.");
        }
        if (!password.equals(password2)) {
            throw new MyException("Passwords must be the same to continue.");
        }
        if (password.length() <= 5) {
            throw new MyException("Password must contain more than 5.");
        }
    }

    public User getOne(String id) {
        return userRepository.getReferenceById(id);
    }

    @Transactional
    public List<User> listarUsuarios() {

        List<User> usuarios = new ArrayList<>();
        usuarios = userRepository.findAll();
        return usuarios;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = userRepository.searchEmail(email);
        if (usuario != null) {
            List<GrantedAuthority> permiso = new ArrayList<>();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permiso.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession sesion = attr.getRequest().getSession(true);

            sesion.setAttribute("usuariosession", usuario);

            return (UserDetails) new User();
        }
        return null;
    }
}
