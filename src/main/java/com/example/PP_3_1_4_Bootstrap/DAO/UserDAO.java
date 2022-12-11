package com.example.PP_3_1_4_Bootstrap.DAO;






import com.example.PP_3_1_4_Bootstrap.model.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface UserDAO {


    public void add(User user);
    public List<User> getList();
    public User getUser(Long id);
    public void deleteUser(Long id);
    public void editUser(User user);



    public UserDetails getUser(String email);

    User getUserEmail(String email);
}
