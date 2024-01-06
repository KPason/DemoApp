package com.kpason.DemoApp.user.service;

import com.kpason.DemoApp.user.model.dto.UserDTO;
import com.kpason.DemoApp.user.model.entity.User;
import com.kpason.DemoApp.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void addUser(UserDTO userDto) {
        try {
            User user = modelMapper.map(userDto, User.class);
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error adding user with ID " + userDto.getId(), e);
        }
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    public UserDTO findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return modelMapper.map(userOptional.get(), UserDTO.class);
        } else {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
    }

    public void deleteUserById(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error deleting user with ID " + userId, e);
        }
    }

    public void updateUser(UserDTO userDTO) {
        try {
            Optional<User> optionalUser = userRepository.findById(userDTO.getId());

            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();

                modelMapper.map(userDTO, existingUser);

                userRepository.save(existingUser);
            }
        } catch(EntityNotFoundException e){
            throw new EntityNotFoundException("User with ID " + userDTO.getId() + " not found");
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error updating user with ID " + userDTO.getId(), e);
        }
    }
}
