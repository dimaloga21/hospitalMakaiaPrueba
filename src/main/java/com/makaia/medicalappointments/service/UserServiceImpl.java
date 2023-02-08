package com.makaia.medicalappointments.service;

import com.makaia.medicalappointments.model.UserE;
import com.makaia.medicalappointments.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserE saveUser(UserE userE) {
        return userRepository.save(userE);
    }

    @Override
    public UserE getUser(Integer idUser) {
        return userRepository.findById(idUser).orElseThrow(() -> {throw new RuntimeException();});
    }

    @Override
    public UserE editUser(Integer idUser, UserE userE) {
        UserE searchedUser = userRepository.findById(idUser).get();
        searchedUser.setType(userE.getType());
        return userRepository.save(searchedUser);
    }

    @Override
    public boolean deleteUser(Integer idUser) {
        try {
            userRepository.deleteById(idUser);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
