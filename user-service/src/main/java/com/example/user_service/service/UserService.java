package com.example.user_service.service;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.dto.UserUpdateDTO;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userRepository.findAll().stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole().getName());
            return dto;
        }).collect(Collectors.toList());

        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found in the system.");
        }

        return users;
    }

    public UserDTO updateUser(String currentUsername, UserUpdateDTO updateDTO) {
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found"));


        user.setUsername(updateDTO.getUsername());
        user.setEmail(updateDTO.getEmail());
        user.setPassword(updateDTO.getNewPassword());

        User updatedUser = userRepository.save(user);

        UserDTO dto = new UserDTO();
        dto.setId(updatedUser.getId());
        dto.setUsername(updatedUser.getUsername());
        dto.setEmail(updatedUser.getEmail());
        dto.setRole(updatedUser.getRole().getName());

        return dto;
    }


}
