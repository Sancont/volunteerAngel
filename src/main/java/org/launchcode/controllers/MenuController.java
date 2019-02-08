package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    CheeseDao cheeseDao;

    @Autowired
    MenuDao menuDao;

    // Request path: /menu
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Menus");
        model.addAttribute("menus", menuDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMenuForm(Model model) {
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCategoryForm(@ModelAttribute @Valid Menu menu, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }
        menuDao.save(menu);
        return "redirect:view/"+menu.getId();
    }

    @RequestMapping(value="view/{menuId}", method=RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId){
        Menu aMenu = menuDao.findOne(menuId);
        model.addAttribute("title", "Menu: " + aMenu.getName());
        model.addAttribute("cheeses", aMenu.getCheeses());
        model.addAttribute("menuId", aMenu.getId());
        return "menu/view";
    }

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String displayAddItemForm(Model model,@PathVariable int menuId) {
        Menu aMenu = menuDao.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(aMenu,cheeseDao.findAll());
        model.addAttribute("title", "Add Item to Menu: " + aMenu.getName());
        model.addAttribute("form", form);
        return "menu/add-item";
    }

    @RequestMapping(value="add-item", method = RequestMethod.POST)
    public String processAddItemForm(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors){
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }
        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        Menu theMenu = menuDao.findOne(form.getMenuId());
        theMenu.addItem(theCheese);
        menuDao.save(theMenu);
        return "redirect:view/"+theMenu.getId();
    }

}