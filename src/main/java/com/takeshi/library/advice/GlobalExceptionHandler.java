package com.takeshi.library.advice;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

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

    /**
     * その他の予期せぬ例外をハンドリング
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        logError(ex);
        return errorResponse("予期せぬエラーが発生しました。");
    }

    private void logError(Exception ex) {
        // ログ出力（@Slf4j が使えるなら log.error(...)）
        System.err.println("例外発生: " + ex.getMessage());
        ex.printStackTrace();
    }

    private ResponseEntity<Map<String, Object>> errorResponse(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", message));
    }
}

