package com.takeshi.library.advice;

import com.takeshi.library.entity.Role;
import com.takeshi.library.entity.UserEntity;
import com.takeshi.library.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {

    private final UserService userService;

    @ModelAttribute("requestUri")
    public String exposeRequestUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("displayName") // ← 氏名を表す変数名に変更
    public String addDisplayNameToModel(Principal principal) {

        if (principal == null) return null;

        String email = principal.getName(); // ← Principalからemailを取得
        UserEntity userEntity = userService.findByEmail(email); // ← DBからユーザー情報取得

        return userEntity != null ? userEntity.getName() : email; // ← 氏名があれば氏名、なければemail
    }

    @ModelAttribute("isAdmin")
    public boolean populateIsAdmin(Principal principal) {

        if (principal == null) {
            System.out.println("principal:null");
            return false;
        }
        System.out.println("principal.getName():" + principal.getName());
        UserEntity currentUserEntity = userService.findByEmail(principal.getName());
        return currentUserEntity.getRole() == Role.ADMIN; //Enum型の比較はequalsではないので注意！
    }
}
