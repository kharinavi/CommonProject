package ru.kharina.study.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kharina.study.dao.OwnerDAO;
import ru.kharina.study.models.Owner;

import javax.validation.Valid;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerDAO ownerDAO;

    @Autowired
    public OwnerController(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("owners", ownerDAO.index());
        return "owners/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("owner", ownerDAO.show(id));
        System.out.println(ownerDAO.ProviderOwner(id).toString());
        return "owners/show";
    }

    @GetMapping("/new")
    public String newOwner(@ModelAttribute("owner") Owner owner) {
        return "owners/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("owner") @Valid Owner owner,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "owners/new";

        ownerDAO.save(owner);
        return "redirect:/owners";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("owner", ownerDAO.show(id));
        return "owners/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "owners/edit";
        }

        ownerDAO.update(id, owner);
        return "redirect:/owners";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ownerDAO.delete(id);
        return "redirect:/owners";
    }
}
