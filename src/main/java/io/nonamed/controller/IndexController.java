package io.nonamed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping(value="/")
    public ModelAndView index(){
        return new ModelAndView("layout/main");
    }

    @ResponseBody
    @RequestMapping(value="/layout_sidebar", produces = "text/html;charset=UTF-8")
    public ModelAndView sidebar(){
        return new ModelAndView("layout/sidebar");
    }

    @ResponseBody
    @RequestMapping(value="/layout_header", produces = "text/html;charset=UTF-8")
    public ModelAndView header(){
        return new ModelAndView("layout/header");
    }
}