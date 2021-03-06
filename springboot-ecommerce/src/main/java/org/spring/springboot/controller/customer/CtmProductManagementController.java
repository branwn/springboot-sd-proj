//商品管理
package org.spring.springboot.controller.customer;


import org.spring.springboot.dao.master.ProductDao;
import org.spring.springboot.domain.Product;
import org.spring.springboot.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/customer")
public class CtmProductManagementController {

    @Autowired
    private ProductManagementService productManagementService;

    @Autowired
    private ProductDao productDao;

    @GetMapping("/productManagement")
    public String listAllProductInMarket(Long productId, String productName, String productTag, Long productTypeId, Model model) {
        model.addAttribute("Product", new Product());
        List<Product> list;
        model.addAttribute("host", "搜索结果");
        Product myProduct = new Product();
        myProduct.setProductId(productId);
        myProduct.setProductName(productName);
        myProduct.setProductTag(productTag);
        myProduct.setProductTypeId(productTypeId);
        myProduct.setProductState(0);
        list = productManagementService.searchProduct(myProduct);
        model.addAttribute("list", list);
        return "customer/productManagement";
    }



//    @PostMapping("/productManagement")
//    public String productInsertOrUpdate(Model model, @ModelAttribute Product product) {
//        if((product.getProductId() == null)){
//            model.addAttribute("host", "ID不能为空");
//            return "customer/inputWarning";
//        }else if(product.getProductQuantity() == null ){
//            model.addAttribute("host", "商品数量不能为空");
//            return "customer/inputWarning";
//        }
//
//        productManagementService.insertOrUpdateProduct(product);
//        return "redirect:/customer/productManagement";
//    }
//
//    @PostMapping("/productDelete")
//    public String deleteUser(Model model, @ModelAttribute Product product) {
//        if(product.getProductId() == null){
//            model.addAttribute("host", "ID不能为空");
//            return "customer/inputWarning";
//        }
//        productManagementService.deleteProduct(product.getProductId());
//        return "redirect:/customer/productManagement";
//    }

}