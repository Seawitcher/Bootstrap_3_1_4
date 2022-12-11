package com.example.PP_3_1_4_Bootstrap.service;




import com.example.PP_3_1_4_Bootstrap.model.Role;

import java.util.List;

public interface RoleService  {

    public boolean add(Role role);
    public List<Role> getList();
    public Role getRole(Long id);
    public void deleteRole(Long id);
    public void editRole(Role role);


    List<Role> listByRole(List<String> name);
}
