package com.takeshi.library.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Renderなどのクラウドサービスによるヘルスチェックに応答するコントローラー。
 * <p>
 * このエンドポイントは、サービスの起動確認や自動再起動の判定に使用されます。
 * 主にインフラレイヤーとの連携を目的としており、ドメインロジックとは分離されています。
 * </p>
 */
@RestController
public class HealthController {

    /**
     * ヘルスチェック用のエンドポイント。
     *
     * @return HTTP 200 OK を返すレスポンス
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}