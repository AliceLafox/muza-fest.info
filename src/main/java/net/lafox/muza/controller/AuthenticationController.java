package net.lafox.muza.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(method = RequestMethod.GET, value = {"login"})
    public String showLoginPage() {
        return "login";
    }

}
