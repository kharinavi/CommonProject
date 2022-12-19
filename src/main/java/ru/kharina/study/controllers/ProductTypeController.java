package ru.kharina.study.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kharina.study.dao.ProductTypeDAO;
import ru.kharina.study.models.ProductType;

import javax.validation.Valid;

@Controller
@RequestMapping("/producttypes")
public class ProductTypeController {

    private final ProductTypeDAO productTypeDAO;

    @Autowired
    public ProductTypeController(ProductTypeDAO productTypeDAO) {
        this.productTypeDAO = productTypeDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("producttypes", productTypeDAO.index());
        return "producttypes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("productType", productTypeDAO.show(id));
        System.out.println(productTypeDAO.getCurrentProductList().size());
        return "producttypes/show";
    }

    @GetMapping("/new")
    public String newProductType(@ModelAttribute("productType") ProductType productType) {
        return "producttypes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("productType") @Valid ProductType productType,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "producttypes/new";

        productTypeDAO.save(productType);
        return "redirect:/producttypes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("productType", productTypeDAO.show(id));
        return "producttypes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("productType") @Valid ProductType productType, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "producttypes/edit";

        productTypeDAO.update(id, productType);
        return "redirect:/producttypes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productTypeDAO.delete(id);
        return "redirect:/producttypes";
    }
}
