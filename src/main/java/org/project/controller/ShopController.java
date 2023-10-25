package org.project.controller;

import org.project.models.Shop;
import org.project.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shops")
    public String listShops(Model model) {
        List<Shop> shops = shopService.getAllShops();
        model.addAttribute("shops", shops);
        return "shop/list";
    }

    @GetMapping("/shops/{id}")
    public String viewShop(@PathVariable Long id, Model model) {
        Shop shop = shopService.getShopById(id);
        model.addAttribute("shop", shop);
        return "shop/details";
    }

    @PostMapping("/new_shop")
    public String createShop(@RequestParam String name, @RequestParam String ownerName) {
        shopService.createShop(name, ownerName);
        return "redirect:/shops";
    }

    @DeleteMapping("/shops/{id}")
    public String deleteShop(@PathVariable Long id) {
        shopService.deleteShop(id);
        return "redirect:/shops";
    }

    @PutMapping("/shops/{id}")
    public String updateShop(@PathVariable Long id, @RequestParam String name, @RequestParam String ownerName) {
        shopService.updateShop(id, name, ownerName);
        return "redirect:/shops";
    }
}