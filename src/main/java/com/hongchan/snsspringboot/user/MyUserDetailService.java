package com.hongchan.snsspringboot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // Sign up process
    public void signUp(User user) {
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUserDetail userDetail = null;
        User user = userRepository.findByUsername(s);

        if(user != null) {
            userDetail = new MyUserDetail();
            userDetail.setUser(user);
        }
        else {
            throw new UsernameNotFoundException("User Not Found [username " + s + "]");
        }
        return userDetail;
    }

}
