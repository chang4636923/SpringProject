package com.chang.weixinsell.controller;

import com.chang.weixinsell.entity.ProductCategory;
import com.chang.weixinsell.form.CategoryForm;
import com.chang.weixinsell.service.IProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private IProductCategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList =
                categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("/category/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        // 检测是否有categoryId传入，如果没传，则为新增类目
        if (categoryId != null) {
            Optional<ProductCategory> productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory.get());
        }
        return new ModelAndView("/category/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        // 入参校验
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/list");
            return new ModelAndView("/comm/error", map);
        }
        // 是否传入categoryId
        if (categoryForm.getCategoryId() != null) {
            Optional<ProductCategory> productCategory = categoryService.findOne(categoryForm.getCategoryId());
            if (productCategory == null) {
                map.put("msg", "【卖家端类目修改】失败,categoryId不存在");
                map.put("url", "/sell/seller/category/list");
                return new ModelAndView("/comm/error", map);
            }
            map.put("msg", "【卖家端类目修改】成功");
            map.put("url", "/sell/seller/category/list");
            BeanUtils.copyProperties(categoryForm, productCategory.get());
            categoryService.save(productCategory.get());
            return new ModelAndView("/comm/success", map);
        }
        // 新增类目
        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(categoryForm, productCategory);
        categoryService.save(productCategory);
        map.put("msg", "【卖家端类目新增】成功");
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("/comm/success", map);
    }
}
