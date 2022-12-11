package com.example.PP_3_1_4_Bootstrap.service;


import com.example.PP_3_1_4_Bootstrap.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface UserService extends UserDetailsService {



    public void add(User user);
    public List<User> getList();
    public User getUser(Long id);
    public void deleteUser(Long id);
  //  public void editUser(User user);

    @Transactional
    void editUser(User user);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    User loadUserByUserEmail(String email)throws UsernameNotFoundException;
}
