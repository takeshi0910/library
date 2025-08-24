package com.takeshi.library.entity

import lombok.Data

import java.time.LocalDate

/**
 * ユーザーによる書籍の貸出履歴を管理するエンティティクラス。
 * 貸出日、返却期限、返却状況などの情報を保持し、貸出機能のロジックに利用される。
 */
@Data
public class Lending {
    /** 貸出ID（主キー） */
    private Long id;

    /** 貸出を行ったユーザーのID */
    private Long userId;

    /** 貸出対象の書籍ID */
    private Long bookId;

    /** 貸出日 */
    private LocalDate lendDate;

    /** 返却期限日 */
    private LocalDate dueDate;

    /** 返却済みかどうかのフラグ（true: 返却済, false: 未返却） */
    private boolean returned;

}
