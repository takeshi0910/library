package com.takeshi.library.controller.view;

import com.takeshi.library.application.user.UserConverter;
import com.takeshi.library.entity.UserEntity;
import com.takeshi.library.form.UserForm;
import com.takeshi.library.security.SecurityService;
import com.takeshi.library.service.UserService;
import com.takeshi.library.entity.Role;
import com.takeshi.library.validation.group.GroupOrder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @ModelAttribute("roles")
    public Role[] populateRoles() {
        return Role.values();
    }

    // 一覧表示
    @GetMapping
    public String list(Model model) {
        List<UserEntity> users = userService.searchUsers(""); // 空文字で検索 → 全件表示
        model.addAttribute("users", users);
        return "users/list";
    }

    // 登録画面表示
    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        return "users/form";
    }

    // 編集画面表示
    @GetMapping("/edit")
    public String showEditForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserEntity userEntity = userService.findByEmail(userDetails.getUsername());
        UserForm userForm = UserConverter.toForm(userEntity);
        model.addAttribute("userForm", userForm);
        return "users/form";
    }

    // 登録・更新処理
    @PostMapping("/save")
    public String save(@ModelAttribute("userForm") @Validated(GroupOrder.class) UserForm userForm, BindingResult result,
                       RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "users/form";
        }

        UserEntity userEntity = UserConverter.toEntity(userForm);

        if (userEntity.getId() == null) {
            userService.insert(userEntity);
            redirectAttributes.addFlashAttribute("message",
                    userEntity.getName() + "　様　ようこそ図書館アプリへ");

            securityService.autoLogin(userEntity.getEmail(), request); // 自動ログイン
            return "redirect:/";

        } else {
            userService.update(userEntity);
            redirectAttributes.addFlashAttribute("message",
                    "ユーザー『" + userEntity.getName() + "さん』の情報を更新しました。");
            return "redirect:/";
        }
    }

    // 削除処理
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // 削除前に名前を取得してメッセージ用に使う（任意）
        UserEntity userEntity = userService.findById(id);
        String name = (userEntity != null) ? userEntity.getName() : "未確認のユーザー";

        userService.softDelete(id); // 実際の削除処理（論理削除など）
        redirectAttributes.addFlashAttribute("message", "ユーザー『" + name + "』を削除しました。");

        return "redirect:/users"; // 一覧に戻る
    }

}