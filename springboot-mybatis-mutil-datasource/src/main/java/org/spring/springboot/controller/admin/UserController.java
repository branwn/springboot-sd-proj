package org.spring.springboot.controller.admin;

import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.spring.springboot.domain.Wallet;
import org.spring.springboot.service.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private WalletService walletService;

    @GetMapping("/user")
    public String listAllUsers(Long id, Model model) {

        model.addAttribute("user", new User());
        List<User> List;
        if (id != null){
            model.addAttribute("host", "搜索结果");
            List = userService.findById(id);
        }else{
            model.addAttribute("host", "所有用户");
            List = userService.findByName("%");
        }
        userService.setCityList(List);
        userService.setWalletList(List);
        model.addAttribute("List", List);
        return "admin/user";
    }

//    @GetMapping("/user/{id}")
//    public String findUsers(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("host", "搜索结果");
//        model.addAttribute("user", new User());
//        List<User> List;
//        List = userService.findById(id);
//        userService.setCity(List);
//        model.addAttribute("List", List);
//        return "admin/user";
//    }

    @PostMapping("/user")
    public String userRegistration(Model model, @ModelAttribute User user) {
        if(user.getId() == null){
            return "admin/inputWarning";
        }
        else if(userService.findById(user.getId()) == null){
            return "admin/inputWarning";
        }
        userService.saveUser(user);
        //create city
        City city = new City();
        city.setId(user.getId());
        cityService.saveCity(city);
        //create wallet
        Wallet wallet = new Wallet();
        wallet.setId(user.getId());
        wallet.setAsset((long) 0);
        walletService.saveWallet(wallet);
        return "redirect:/user";
    }

    @PostMapping("/userDelete")
    public String deleteUser(Model model, @ModelAttribute User user) {
        if(user.getId() == null){
            return "admin/inputWarning";
        }
        userService.deleteUser(user.getId());
        cityService.deleteCity(user.getId());
        walletService.deleteWallet(user.getId());
        return "redirect:/user";
    }

}