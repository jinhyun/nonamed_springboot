package io.nonamed.controller.organization;

import io.nonamed.domain.organization.Organ;
import io.nonamed.domain.organization.OrganJson;
import io.nonamed.service.organization.OrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrganController {
    @Autowired
    OrganService organService;

    @RequestMapping(value="/organ")
    @ResponseBody
    public ModelAndView indexOrgan() {
        List<Organ> organList = organService.getOrganAllList();

        OrganJson organJson = new OrganJson();
        String resultJson = organJson.toJsonOrganList(organList);

        return new ModelAndView("organ").addObject("organJson", resultJson);
    }
}