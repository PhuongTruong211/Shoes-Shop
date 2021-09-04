package com.phuong.application.controller.admin;

import com.phuong.application.entity.Category;
import com.phuong.application.model.dto.CategoryDTO;
import com.phuong.application.model.mapper.CategoryMapper;
import com.phuong.application.model.request.CreateCategoryRequest;
import com.phuong.application.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String homePage(Model model,
                           @RequestParam(defaultValue = "",required = false) String id,
                           @RequestParam(defaultValue = "",required = false) String name,
                           @RequestParam(defaultValue = "",required = false) String status,
                           @RequestParam(defaultValue = "1",required = false) Integer page){

        Page<Category> categories = categoryService.adminGetListCategory(id,name,status,page);
        model.addAttribute("categories",categories.getContent());
        model.addAttribute("totalPages",categories.getTotalPages());
        model.addAttribute("currentPage", categories.getPageable().getPageNumber() + 1);

        return "admin/category/list";
    }


    @GetMapping("/api/admin/categories")
    public ResponseEntity<Page<Category>> adminGetListCategories(@RequestParam(defaultValue = "",required = false) String id,
                                                                 @RequestParam(defaultValue = "",required = false) String name,
                                                                 @RequestParam(defaultValue = "",required = false) String status,
                                                                 @RequestParam(defaultValue = "0",required = false) Integer page){
        Page<Category> categories = categoryService.adminGetListCategory(id,name,status,page);
        return ResponseEntity.ok(categories);

    }
    @GetMapping("/api/admin/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(CategoryMapper.toCategoryDTO(category));
    }

    @PostMapping("/api/admin/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category category = categoryService.createCategory(createCategoryRequest);
        return ResponseEntity.ok(CategoryMapper.toCategoryDTO(category));
    }

    @PutMapping("/api/admin/categories/{id}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest, @PathVariable long id) {
        categoryService.updateCategory(createCategoryRequest, id);
        return ResponseEntity.ok("Sửa danh mục thành công!");
    }

    @DeleteMapping("/api/admin/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Xóa danh mục thành công!");
    }

    @PutMapping("/api/admin/categories")
    public ResponseEntity<String> updateOrderCategory(@RequestBody int[] ids){
        categoryService.updateOrderCategory(ids);
        return ResponseEntity.ok("Thay đổi thứ tự thành công!");
    }
}
