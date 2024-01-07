package com.project.shopapp.Service;

import com.project.shopapp.Repository.RoleRepository;
import com.project.shopapp.Repository.UserRepository;
import com.project.shopapp.dtos.UserDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.Role;
import com.project.shopapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository ;
    @Override
    public User createUser(UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number exits ");
        }
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();
        Role exitsRole;
        try {
            exitsRole = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new DataNotFoundException("Role not found"));
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
        newUser.setRole(exitsRole);
        if(userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0){

        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        return null;
    }
}
