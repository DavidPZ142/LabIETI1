package com.edu.eci.ieti.laboratorio.service;

import com.edu.eci.ieti.laboratorio.DTO.UserDto;
import com.edu.eci.ieti.laboratorio.entity.User;
import com.edu.eci.ieti.laboratorio.exception.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private HashMap<String, User> users = new HashMap<>();

    @Override
    public User create(User user) throws UserException {
        if (users.containsKey(user.getId())){
            throw new UserException("Id ya registrada");
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        for(String id: users.keySet()){
            userList.add(users.get(id));
        }
        return userList;
    }

    @Override
    public void deleteById(String id) throws UserException {
        if(!users.containsKey(id)){
            throw new UserException("No existe ese usuario");
        }
        users.remove(id);
    }

    @Override
    public User update(User user, String id) throws UserException {
        if(!users.containsKey(id)){
            throw new UserException("No existe ese usuario");
        }
        users.remove(id);
        users.put(id,user);
        return user;
    }

    @Override
    public List<UserDto> convert (List<User> users){
        ModelMapper modelMapper = new ModelMapper();
        List<UserDto> listamapeada = new ArrayList<>();
        for (User user: users){
            UserDto userDto = modelMapper.map(user,UserDto.class);
            listamapeada.add(userDto);
        }
        return listamapeada;
    }
}
