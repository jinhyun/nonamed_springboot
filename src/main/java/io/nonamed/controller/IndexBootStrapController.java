package io.nonamed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexBootStrapController {
    @RequestMapping(value="/boot")
    public ModelAndView indexBootstrap() {
        return new ModelAndView("indexBootstrap");
    }
}
