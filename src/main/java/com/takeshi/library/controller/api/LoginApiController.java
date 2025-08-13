package com.takeshi.library.controller.api;

import com.takeshi.library.domain.auth.AuthResult;
import com.takeshi.library.form.LoginRequest;
import com.takeshi.library.security.SecurityService;
import com.takeshi.library.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final AuthService authService;
    private final UserDetailsService userDetailsService;
    private final SecurityService securityService;

    @PostMapping("/api/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {

        // ① 認証チェック（メールアドレスとパスワード）
        try {
            AuthResult result = authService.authenticate(request.getEmail(), request.getPassword());
            if (result != AuthResult.SUCCESS) {
                return failedResponse(result); // 401 + メッセージ
            }
        } catch (DataAccessException e) {
            log.error("認証処理中にDBエラーが発生しました", e);
            return errorResponse("ログイン処理中にエラーが発生しました。"); // 500 + メッセージ
        }

        // ② 認証情報の作成（UserDetails → Authentication）
        Authentication authentication = securityService.createAuthentication(request.getEmail());

        // ③ SecurityContext に認証情報をセット
        securityService.setSecurityContext(authentication);

        // ④ セッションに SecurityContext を保存
        securityService.saveSecurityContextToSession(httpRequest);

        // ⑤ 認証成功レスポンスを返却
        return successResponse(); // 200 + success: true
    }

    /**
     * 認証失敗時のレスポンスを構築する。
     * HTTPステータス 401（Unauthorized）と、失敗メッセージを含むJSONを返す。
     *
     * @param result 認証結果（失敗時のメッセージを含む）
     * @return 認証失敗レスポンス（success: false, message: ...）
     */
    private ResponseEntity<Map<String, Object>> failedResponse(AuthResult result) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("success", false, "message", result.getMessage()));
    }

    /**
     * 認証成功時のレスポンスを構築する。
     * HTTPステータス 200（OK）と、成功フラグのみを含むJSONを返す。
     *
     * @return 認証成功レスポンス（success: true）
     */
    private ResponseEntity<Map<String, Object>> successResponse() {
        return ResponseEntity.ok(Map.of("success", true));
    }

    /**
     * システムエラー時のレスポンスを構築する。
     * HTTPステータス 500（Internal Server Error）と、失敗メッセージを含むJSONを返す。
     *
     * @param message エラーメッセージ
     * @return システムエラーレスポンス（success: false, message: ...）
     */
    private ResponseEntity<Map<String, Object>> errorResponse(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", message));
    }

}
