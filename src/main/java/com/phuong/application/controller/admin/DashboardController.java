package com.phuong.application.controller.admin;

import com.phuong.application.repository.BrandRepository;
import com.phuong.application.repository.CategoryRepository;
import com.phuong.application.repository.ProductRepository;
import com.phuong.application.repository.StatisticRepository;
import com.phuong.application.repository.UserRepository;
import com.phuong.application.model.dto.ChartDTO;
import com.phuong.application.model.dto.StatisticDTO;
import com.phuong.application.model.request.FilterDayByDay;
import com.phuong.application.service.BrandService;
import com.phuong.application.service.CategoryService;
import com.phuong.application.service.OrderService;
import com.phuong.application.service.PostService;
import com.phuong.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private PostService postService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String dashboard(Model model){
        return "admin/index";
    }

    @GetMapping("/api/admin/count/posts")
    public ResponseEntity<Long> getCountPost(){
        long countPosts = postService.getCountPost();
        return ResponseEntity.ok(countPosts);
    }

    @GetMapping("/api/admin/count/products")
    public ResponseEntity<Long> getCountProduct(){
        long countProducts = productService.getCountProduct();
        return ResponseEntity.ok(countProducts);
    }

    @GetMapping("/api/admin/count/orders")
    public ResponseEntity<Long> getCountOrders(){
        long countOrders = orderService.getCountOrder();
        return ResponseEntity.ok(countOrders);
    }

    @GetMapping("/api/admin/count/categories")
    public ResponseEntity<Long> getCountCategories(){
        long countCategories = categoryService.getCountCategories();
        return ResponseEntity.ok(countCategories);
    }

    @GetMapping("/api/admin/count/brands")
    public ResponseEntity<Long> getCountBrands(){
        long countBrands = brandService.getCountBrands();
        return ResponseEntity.ok(countBrands);
    }

    @GetMapping("/api/admin/count/users")
    public ResponseEntity<Long> getCountUsers(){
        long countUsers = userRepository.count();
        return ResponseEntity.ok(countUsers);
    }

    @GetMapping("/api/admin/statistics")
    public ResponseEntity<List<StatisticDTO>> getStatistic30Day(){
        List<StatisticDTO> statistics = statisticRepository.getStatistic30Day();
        return ResponseEntity.ok(statistics);
    }

    @PostMapping("/api/admin/statistics")
    public ResponseEntity<List<StatisticDTO>> getStatisticDayByDay(@RequestBody FilterDayByDay filterDayByDay){
        List<StatisticDTO> statisticDTOS = statisticRepository.getStatisticDayByDay(filterDayByDay.getToDate(),filterDayByDay.getFromDate());
        return ResponseEntity.ok(statisticDTOS);
    }

    @GetMapping("/api/admin/product-order-categories")
    public ResponseEntity<List<ChartDTO>> getListProductOrderCategories(){
        List<ChartDTO> chartDTOS = categoryRepository.getListProductOrderCategories();
        return ResponseEntity.ok(chartDTOS);
    }

    @GetMapping("/api/admin/product-order-brands")
    public ResponseEntity<List<ChartDTO>> getProductOrderBrands(){
        List<ChartDTO> chartDTOS = brandRepository.getProductOrderBrands();
        return ResponseEntity.ok(chartDTOS);
    }

    @GetMapping("/api/admin/product-order")
    public ResponseEntity<List<ChartDTO>> getProductOrder(){
        Pageable pageable = PageRequest.of(0,10);
        Date date = new Date();
        List<ChartDTO> chartDTOS = productRepository.getProductOrders(pageable, date.getMonth() +1, date.getYear() + 1900);
        return ResponseEntity.ok(chartDTOS);
    }
}
