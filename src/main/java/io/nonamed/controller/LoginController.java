package io.nonamed.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.nonamed.domain.user.User;
import io.nonamed.service.organization.OrganService;
import io.nonamed.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@SessionAttributes("userSession")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    OrganService organService;

    @RequestMapping(value="/login")
    public ModelAndView login(){
        organService.insertOrgan_initData();
        return new ModelAndView("login").addObject("message", "Nonamed World");
    }

    @RequestMapping(value="/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(User user, HttpSession session){
        // Valid userInfo (id, password)
        ModelAndView modelAndView = new ModelAndView();
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();

        User dbUser = userService.getUser(user.getUserId());
        JsonObject jsonObject = new JsonObject();

        if (dbUser == null){
            jsonObject.addProperty("statusCode", "fail");
            jsonObject.addProperty("message", "user is null");
            jsonObject.addProperty("user", "");

        } else {
            jsonObject.addProperty("statusCode", "success");
            jsonObject.addProperty("message", "");
            jsonObject.add("user", gson.toJsonTree(dbUser, User.class));

            session.setAttribute("userSession" , dbUser);
        }
        return gson.toJson(jsonObject);
    }
}