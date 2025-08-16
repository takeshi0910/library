package com.takeshi.library.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "accountDeleted", required = false) String accountDeleted,
                                Model model) {
        if (accountDeleted != null) {
            model.addAttribute("infoMessage", "アカウントは正常に削除されました。ご利用ありがとうございました。");
        }
        return "login";
        }
    }