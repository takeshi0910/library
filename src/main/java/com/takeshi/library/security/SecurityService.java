package com.takeshi.library.security;

import com.takeshi.library.entity.UserEntity;
import com.takeshi.library.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserDetailsService userDetailsService;

    /**
     * 指定されたメールアドレスのユーザーをログイン状態にする。
     * <p>
     * 主にユーザー登録直後など、明示的なログイン操作なしで
     * 自動的にログイン状態へ移行させたい場面で使用する。
     *
     * @param email 自動ログインさせたいユーザーのメールアドレス
     * @throws UsernameNotFoundException メールアドレスに対応するユーザーが存在しない場合
     */
    public void autoLogin(String email, HttpServletRequest request) {
        Authentication authentication = createAuthentication(email);
        setSecurityContext(authentication);
        saveSecurityContextToSession(request);
    }

    /**
     * 指定されたメールアドレスに対応するユーザー情報から、
     * 認証済みの Authentication オブジェクトを作成する。
     *
     * @param email ユーザーのメールアドレス
     * @return 認証済みの Authentication オブジェクト
     */
    public Authentication createAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    /**
     * SecurityContext に Authentication をセットする。
     *
     * @param authentication 認証済みの Authentication オブジェクト
     */
    public void setSecurityContext(Authentication authentication) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    /**
     * 認証情報を含む SecurityContext をセッションに保存する。
     *
     * @param request HTTPリクエスト（セッション取得に使用）
     */
    public void saveSecurityContextToSession(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        SecurityContext context = SecurityContextHolder.getContext();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
    }

}
