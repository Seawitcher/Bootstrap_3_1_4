package com.example.PP_3_1_4_Bootstrap.configs;

import com.example.PP_3_1_4_Bootstrap.model.Role;
import org.springframework.core.convert.converter.Converter;


public class StringRoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String id) {
        Role role = new Role();
        role.setId(Long.valueOf(id));
        return role;
    }
}


