package ru.kharina.study.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kharina.study.dao.ProductDAO;
import ru.kharina.study.models.Product;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productDAO.index());
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.show(id));
        System.out.println(productDAO.getCurrentProductList().size());
        System.out.println(productDAO.getCurrentProvider().toString());
        return "products/show";
    }

    @GetMapping("/new")
    public String newProductType(@ModelAttribute("product") Product productType) {
        return "products/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product productType,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "products/new";

        productDAO.save(productType);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productDAO.show(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("productType") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "products/edit";

        productDAO.update(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.delete(id);
        return "redirect:/products";
    }
}
