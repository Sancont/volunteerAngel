package org.Sandra.controllers;

import org.Sandra.models.Ministry;
import org.Sandra.models.Volunteer;
import org.Sandra.models.data.MinistryDao;
import org.Sandra.models.data.VolunteerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Sandra
 */
@Controller
@RequestMapping("volunteer")
public class VolunteerController {

    @Autowired
    VolunteerDao volunteerDao;

    @Autowired
    MinistryDao ministryDao;

    // Request path: /volunteer
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("volunteers", volunteerDao.findAll());
        model.addAttribute("title", "Volunteers");

        return "volunteer/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddVolunteerForm(Model model) {
        model.addAttribute("title", "Add Volunteer");
        model.addAttribute(new Volunteer());
        model.addAttribute("ministries", ministryDao.findAll());
        return "volunteer/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddVolunteerForm(@ModelAttribute  @Valid Volunteer newVolunteer,
                                       Errors errors, @RequestParam int ministryId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Volunteer");
            model.addAttribute("ministries", ministryDao.findAll());
            return "volunteer/add";
        }

        Ministry cat= ministryDao.findOne(ministryId);
        newVolunteer.setMinistry(cat);
        volunteerDao.save(newVolunteer);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveVolunteerForm(Model model) {
        model.addAttribute("volunteers", volunteerDao.findAll());
        model.addAttribute("title", "Remove Volunteer");
        return "volunteer/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveVolunteerForm(@RequestParam int[] ids) {
        for (int id : ids) {
            volunteerDao.delete(id);
        }
        return "redirect:";
    }
    @RequestMapping(value="ministry", method=RequestMethod.GET)
    public String category(Model model, @RequestParam int id){
        Ministry cat = ministryDao.findOne(id);
        List<Volunteer> volunteers = cat.getVolunteers();
        model.addAttribute("volunteers", volunteers);
        model.addAttribute("title", "Volunteers in Ministry: " + cat.getName());
        return "volunteer/index";
    }
}
