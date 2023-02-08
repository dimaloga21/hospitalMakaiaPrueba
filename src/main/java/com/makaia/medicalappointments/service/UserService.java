package com.makaia.medicalappointments.service;

import com.makaia.medicalappointments.model.UserE;


public interface UserService {

    UserE saveUser(UserE userE);

    UserE getUser(Integer idUser);

    UserE editUser(Integer idUser, UserE userE);

    boolean deleteUser(Integer idUser);
}
