package org.Sandra.controllers;

import org.Sandra.models.Volunteer;
import org.Sandra.models.Mass;
import org.Sandra.models.data.VolunteerDao;
import org.Sandra.models.data.MassDao;
import org.Sandra.models.forms.AddMassVolunteerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("mass")
public class MassController extends AbstractBaseController {
    @Autowired
    VolunteerDao volunteerDao;

    @Autowired
    MassDao massDao;

    // Request path: /mass
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Masses");
        model.addAttribute("masses", massDao.findAll());
        return "mass/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMassForm(Model model) {
        model.addAttribute("title", "Add Mass");
        model.addAttribute(new Mass());
        return "mass/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMassForm(@ModelAttribute @Valid Mass mass, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Mass");
            return "mass/add";
        }
        massDao.save(mass);
        return "redirect:view/"+ mass.getId();
    }

    @RequestMapping(value="view/{massId}", method=RequestMethod.GET)
    public String viewMass(Model model, @PathVariable int massId){
        Mass aMass = massDao.findOne(massId);
        model.addAttribute("title", "Mass: " + aMass.getName());
        model.addAttribute("volunteers", aMass.getVolunteers());
        model.addAttribute("massId", aMass.getId());
        return "mass/view";
    }

    @RequestMapping(value = "add-volunteer/{massId}", method = RequestMethod.GET)
    public String displayAddVolunteerForm(Model model,@PathVariable int massId) {
        Mass aMass = massDao.findOne(massId);
        AddMassVolunteerForm form = new AddMassVolunteerForm(aMass,volunteerDao.findAll());
        model.addAttribute("title", "Add Volunteer to Mass: " + aMass.getName());
        model.addAttribute("form", form);
        return "mass/add-volunteer";
    }

    @RequestMapping(value="add-volunteer", method = RequestMethod.POST)
    public String processAddVolunteerForm(Model model, @ModelAttribute @Valid AddMassVolunteerForm form, Errors errors){
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "mass/add-volunteer";
        }
        Volunteer theVolunteer = volunteerDao.findOne(form.getVolunteerId());
        Mass theMass = massDao.findOne(form.getMassId());
        theMass.addItem(theVolunteer);
        massDao.save(theMass);
        return "redirect:view/"+ theMass.getId();
    }

}
