package org.Sandra.controllers;

import org.Sandra.models.Ministry;

import org.Sandra.models.data.MinistryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("ministry")
public class MinistryController extends AbstractBaseController {
    @Autowired
    private MinistryDao ministryDao;

    // Request path: /ministry
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("ministries", ministryDao.findAll());
        model.addAttribute("title", "Ministries");
        return "ministry/index";
    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMinistryForm(Model model) {
        model.addAttribute("title", "Add Ministry");
        model.addAttribute(new Ministry());
        model.addAttribute("ministries", ministryDao.findAll());
        return "ministry/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMinistryForm(@ModelAttribute @Valid Ministry ministry, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ministry");
            return "ministry/add";
        }
        ministryDao.save(ministry);
        return "redirect:";
    }
}
