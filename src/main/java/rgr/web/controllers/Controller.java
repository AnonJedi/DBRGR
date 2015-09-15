package rgr.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rgr.web.service.Service;

/**
 * Created by vik on 09.09.15.
 */

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private Service service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getMain() {
        return new ModelAndView("main");
    }
}
