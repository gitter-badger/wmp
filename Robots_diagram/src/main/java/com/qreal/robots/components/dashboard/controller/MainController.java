package com.qreal.robots.components.dashboard.controller;

import com.qreal.robots.common.utils.AuthenticatedUser;
import com.qreal.robots.components.authorization.model.auth.User;
import com.qreal.robots.components.database.users.service.client.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView home(HttpSession session) {
        logger.info("User {} requested main page.", AuthenticatedUser.getUserName());

        User user = userService.findByUserName(AuthenticatedUser.getUserName());

        ModelAndView model = new ModelAndView();
        model.addObject("user", user);
        model.addObject("robots", user.getRobots());
        model.setViewName("dashboard/index");

        logger.info("For user {} main page was created", AuthenticatedUser.getUserName());

        return model;
    }

}
