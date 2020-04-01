package com.hongchan.snsspringboot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void signUp(User user) {
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAccount userAccount = null;
        final Optional<User> user = userRepository.findById(s);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User Not Found [username " + s + "]");
        }

        userAccount = new UserAccount();
        userAccount.setUser(user.get());

        return userAccount;
    }

    public User getUser(String username) {
        final Optional<User> user = userRepository.findById(username);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User Not Found [username " + username + "]");
        }

        return user.get();
    }

    // 사용자 데이터베이스에서 랜덤으로 한 명을 반환
    public User getRandomUser() {
        long cntUser = userRepository.count();
        int index = (int)(Math.random() * cntUser);

        Page<User> userPage = userRepository.findAll(PageRequest.of(index, 1));

        User user = null;
        if(userPage.hasContent()) {
            user = userPage.getContent().get(0);
        }

        return user;
    }

    // 다섯명의 랜덤 사용자 리스트를 반환
    public List<User> getFiveRandomUser(User currentUser) {
        List<User> userList = new ArrayList<>();

        while(userList.size() < 5) {
            User randomUser = getRandomUser();

            if(currentUser.getUsername().equals(randomUser.getUsername())) continue;
            if(userList.contains(randomUser)) continue;

            userList.add(randomUser);
        }

        return userList;
    }

}
