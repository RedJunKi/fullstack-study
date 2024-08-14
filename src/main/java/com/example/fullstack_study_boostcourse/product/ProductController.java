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
        ProductResponse result;

        if (categoryId == null || categoryId == 0) {
            result = productService.getAllProducts(start);
        } else {
            result = productService.getAllProductsByCategory(categoryId, start);

        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("{displayInfoId}")
    public ResponseEntity<DisplayInfoResponse> getDisplayInfo(@PathVariable("displayInfoId") int displayInfoId) {
        DisplayInfoResponse displayInfoById = productService.getDisplayInfoById(displayInfoId);
        return ResponseEntity.ok(displayInfoById);
    }
}
