package com.rochestapelberg.PRG381Project.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rochestapelberg.PRG381Project.model.ApplicationUser;
import com.rochestapelberg.PRG381Project.model.User;
import com.rochestapelberg.PRG381Project.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public ApplicationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);

        user.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        return user.map(ApplicationUser::new).get();
    }

    public List<User> loadStudents() {
        List<User> students = userRepository.findByRoles("ROLE_STUDENT");

        return students;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void saveStudent(User user){

        userRepository.save(user);
    }

    public Optional<User> getStudentByUsername(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        return user;
    }

    public User getStudentById(int id) {

        return userRepository.findById(id).get();
    }

    public void deleteStudent(int id) {
        userRepository.deleteById(id);
    }
}
