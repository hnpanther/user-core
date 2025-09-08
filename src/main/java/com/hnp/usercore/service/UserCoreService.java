package com.hnp.usercore.service;

import com.hnp.usercore.dto.UserCoreDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserCoreService {

    UserCoreDTO createUser(UserCoreDTO userCoreDTO);
    UserCoreDTO getUserById(Long id);
    UserCoreDTO getUserByEmail(String email);
    UserCoreDTO getUserByUsername(String username);
    UserCoreDTO getUserByPhoneNumber(String phoneNumber);
    List<UserCoreDTO> getAllUsers();
}
