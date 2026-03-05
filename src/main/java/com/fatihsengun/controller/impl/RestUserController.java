package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestUserController;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.service.IUserService;
import com.fatihsengun.service.impl.UserServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RestUserController extends RestRootResponseController implements IRestUserController {

    @Autowired
    private UserServiceImpl userService;


    @Override
    @GetMapping()
    public DtoUser getUser() {
        return userService.getUser();
    }
}
