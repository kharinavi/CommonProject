package ru.kharina.study.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kharina.study.dao.ProviderDAO;
import ru.kharina.study.models.Provider;

import javax.validation.Valid;

@Controller
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderDAO providerDAO;

    @Autowired
    public ProviderController(ProviderDAO providerDAO) {
        this.providerDAO = providerDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("providers", providerDAO.index());
        return "providers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("provider", providerDAO.show(id));
        System.out.println(providerDAO.getCurrentProductList().size());
        System.out.println(providerDAO.getCurrentOwner().toString());
        return "providers/show";
    }

    @GetMapping("/new")
    public String newProvider(@ModelAttribute("provider") Provider provider) {
        return "providers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("provider") @Valid Provider provider,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "providers/new";

        providerDAO.save(provider);
        return "redirect:/providers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("provider", providerDAO.show(id));
        return "providers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("provider") @Valid Provider provider, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "providers/edit";

        providerDAO.update(id, provider);
        return "redirect:/providers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        providerDAO.delete(id);
        return "redirect:/providers";
    }
}
