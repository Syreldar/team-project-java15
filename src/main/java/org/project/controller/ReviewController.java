package org.project.controller;



import org.project.models.Customer;
import org.project.models.ProductReview;
import org.project.models.ShopReview;
import org.project.services.ProductReviewService;
import org.project.services.ShopReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private final ShopReviewService shopReviewService;
    @Autowired
    private final ProductReviewService productReviewService;

    @Autowired
    public ReviewController(ShopReviewService shopReviewService, ProductReviewService productReviewService) {
        this.shopReviewService = shopReviewService;
        this.productReviewService = productReviewService;
    }

    @GetMapping("/shop/{id}")
    public String listShopReviews(@PathVariable Long id, Model model) {
        List<ShopReview> shopReviews = shopReviewService.getShopReviewsByShopId(id);
        model.addAttribute("shopReviews", shopReviews);
        return "shop/reviews";
    }

    @GetMapping("/product/{id}")
    public String listProductReviews(@PathVariable Long id, Model model) {
        List<ProductReview> productReviews = productReviewService.getProductReviewsByProductId(id);
        model.addAttribute("productReviews", productReviews);
        return "product/reviews";
    }

    @PostMapping("/new_shop_review/{id}")
    public String createShopReview(@PathVariable Long id, @RequestParam float rating, @RequestParam String comment ,@RequestParam Customer customer) {
        shopReviewService.createShopReview(id, rating, comment, customer);
        return "redirect:/reviews/shop/" + id;
    }

    @PostMapping("/new_product_review/{id}")
    public String createProductReview(@PathVariable Long id,  @RequestParam Customer reviewer, @RequestParam float rating, @RequestParam String comment) {
        productReviewService.createProductReview(id, rating, comment, reviewer);
        return "redirect:/reviews/product/" + id;
    }

    @DeleteMapping("/shop/{id}")
    public String deleteShopReview(@PathVariable Long id) {
        shopReviewService.deleteShopReview(id);
        // Implement redirect logic based on your requirements.
        return "redirect:/reviews";
    }

    @DeleteMapping("/product/{id}")
    public String deleteProductReview(@PathVariable Long id) {
        productReviewService.deleteProductReview(id);
        // Implement redirect logic based on your requirements.
        return "redirect:/reviews";
    }

}