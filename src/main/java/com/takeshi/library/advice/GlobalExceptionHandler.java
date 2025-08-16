package com.takeshi.library.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * DBアクセス系の例外をハンドリング
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccessException(DataAccessException ex) {
        logError(ex); // ログ出力（技術的な詳細はここに）
        return errorResponse("システムエラーが発生しました。しばらくしてから再度お試しください。");
    }

    private ResponseEntity<Map<String, Object>> errorResponse(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", message));
    }

    /**
     * 404エラーページへ内部転送するための共通メソッド。
     */
    private String forwardTo404() {
        return "error/404";
    }

    /**
     * 存在しないURL（Controller未定義）にアクセスされた場合の404エラーハンドリング。
     *
     * @param ex    発生した {@link NoHandlerFoundException}
     * @param model Viewに渡すモデル（未使用）
     * @return {@code forward:/error/404}
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(NoHandlerFoundException ex, Model model) {
        return forwardTo404();
    }

    /**
     * その他の例外（500エラー）をハンドリングする。
     * <p>
     * Spring Boot 3.x以降では、静的リソース（CSS/JSなど）が存在しない場合に
     * {@link org.springframework.web.servlet.resource.NoResourceFoundException} が発生するため、
     * その場合は404として処理する。
     * <br>
     * それ以外の例外は500エラーとしてログ出力し、 {@code templates/error/500.html} を描画する。
     *
     * @param ex    発生した例外
     * @param model Viewに渡すモデル（エラーメッセージを含む）
     * @return {@code forward:/error/404} または {@code error/500}
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        if (ex instanceof NoResourceFoundException) {
            return forwardTo404();
        }
        logError(ex);
        model.addAttribute("message", "予期せぬエラーが発生しました。");
        return "error/500";
    }

    /**
     * 例外の内容を標準エラー出力にログ出力する。
     * <p>
     * {@code @Slf4j} を使用している場合は {@code log.error(...)} に置き換えるとよりスマート。
     *
     * @param ex 発生した例外
     */
    private void logError(Exception ex) {
        log.error("例外発生: {}", ex.getMessage(), ex);
    }

}

