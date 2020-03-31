package com.hongchan.snsspringboot.user;

import com.hongchan.snsspringboot.follow.FollowService;
import com.hongchan.snsspringboot.follow.Follower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService implements UserDetailsService {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private FollowService followService;

    // Sign up process
    public void signUp(User user) {
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAccount userAccount = null;
        Optional<User> user = userRepository.findById(s);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User Not Found [username " + s + "]");
        }

        userAccount = new UserAccount();
        userAccount.setUser(user.get());

        return userAccount;
    }

    public List<User> getFollowerList(User user) {
        final List<User> followerList = followService.getFollowerList(user);
        return followerList;
    }

    public List<User> getFollowingList(User user) {
        final List<User> followingList = followService.getFollowingList(user);
        return followingList;
    }

}
