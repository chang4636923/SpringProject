package com.chang.weixinsell.controller;

import com.chang.weixinsell.entity.ProductCategory;
import com.chang.weixinsell.entity.ProductInfo;
import com.chang.weixinsell.form.ProductForm;
import com.chang.weixinsell.service.IProductCategoryService;
import com.chang.weixinsell.service.IProductService;
import com.chang.weixinsell.util.GenerateKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductCategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("/product/list", map);
    }

    @GetMapping("/onSale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (Exception e) {
            log.error("【商品上架】异常,{}", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/comm/error", map);
        }
        map.put("msg", "商品上架成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/comm/success", map);
    }

    @GetMapping("/offSale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (Exception e) {
            log.error("【商品下架】异常,{}", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/comm/error", map);
        }
        map.put("msg", "商品下架成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/comm/success", map);
    }

    /**
     * 修改(新增)商品
     *
     * @param productId 要修改的商品Id，如果没有传此字段则为新增商品
     * @param map       返回给前端数据
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            Optional<ProductInfo> productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo.get());
        }
        // 查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("/product/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        // 表单入参校验
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/comm/error", map);
        }
        Optional<ProductInfo> productInfo = Optional.of(new ProductInfo());
        try {
            if (!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = productService.findOne(productForm.getProductId());
            } else {
                // 如果表单中没有productId属性，则为新增商品,获取一个随机productId
                productForm.setProductId(GenerateKeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(productForm, productInfo.get());
            productService.save(productInfo.get());
        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/comm/error", map);
        }
        map.put("msg", "商品信息保存成功!");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/comm/success", map);
    }
}
