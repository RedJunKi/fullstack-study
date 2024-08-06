package com.example.fullstack_study_boostcourse.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.fullstack_study_boostcourse.guestbook.service.GuestbookService.LIMIT;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ProductResponse> getAllProducts(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                                        @RequestParam(value = "start", required = false, defaultValue = "0") int start) {
        List<Product> products;

        if (categoryId == null) {
            products = productService.getAllProducts(start);
        } else {
            products = productService.getAllProductsByCategory(categoryId, start);

        }

        ProductResponse result = new ProductResponse(products);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{displayInfoId}")
    public ResponseEntity<DisplayInfoResponse> getDisplayInfo(@PathVariable("displayInfoId") int displayInfoId) {
        DisplayInfoResponse displayInfoById = productService.getDisplayInfoById(displayInfoId);
        return ResponseEntity.ok(displayInfoById);
    }
}
