package com.takeshi.library.controller.user;

import com.takeshi.library.form.UserForm;
import com.takeshi.library.model.entity.User;
import com.takeshi.library.model.entity.enums.Role;
import com.takeshi.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("isAdmin")
    public boolean populateIsAdmin(Principal principal) {
    // TODO 権限判定 今は true 固定　
    /* if (principal == null) return false; // 未ログイン時の安全対策
       User currentUser = userService.findByMail(principal.getName()); // ログイン時に使った「username」フィールドの値を返すメソッド
       return currentUser.getRole().equals("ADMIN");
    */
        return true;
    }

    @ModelAttribute("roles")
    public Role[] populateRoles() {
        return Role.values();
    }

    // 一覧表示
    @GetMapping
    public String list(Model model) {
        List<User> users = userService.searchUsers(""); // 空文字で検索 → 全件表示
        model.addAttribute("users", users);
        return "users/list";
    }

    // 登録画面表示
    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {


        User user = (id != null) ? userService.findById(id) : new User();
        UserForm userForm = new UserForm();
        userForm.setName(user.getName());
        userForm.setEmail(user.getEmail());

        model.addAttribute("userForm", userForm);
        model.addAttribute("isEdit", id != null);
        return "users/form"; // HTMLは form.html に統一
    }

    // 登録・更新処理
    @PostMapping("/save")
    public String save(@ModelAttribute("userForm") @Validated UserForm userForm, BindingResult result,
                       RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "users/form";
        }

        User user = new User(userForm);

        if (user.getId() == null) {
            userService.insert(user);
            redirectAttributes.addFlashAttribute("message",
                    "『" + user.getName() + "さん』の情報を登録しました。");
        } else {
            userService.update(user);
            redirectAttributes.addFlashAttribute("message",
                    "ユーザー『" + user.getName() + "さん』の情報を更新しました。");
        }

        return "redirect:/users";
    }

    // 削除処理
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // 削除前に名前を取得してメッセージ用に使う（任意）
        User user = userService.findById(id);
        String name = (user != null) ? user.getName() : "未確認のユーザー";

        userService.softDelete(id); // 実際の削除処理（論理削除など）
        redirectAttributes.addFlashAttribute("message", "ユーザー『" + name + "』を削除しました。");

        return "redirect:/users"; // 一覧に戻る
    }


}