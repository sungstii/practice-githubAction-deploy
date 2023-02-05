package notice.board.post.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import notice.board.member.entity.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;


    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 99999)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_REGISTRATION;

    @Enumerated(EnumType.STRING)
    private SecretStatus secretStatus = SecretStatus.PUBLIC;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt;

    public enum QuestionStatus {
        // 질문등록
        QUESTION_REGISTRATION(1, "질문 등록 상태"),
        // 답변완료
        QUESTION_ANSWERED(2, "답변 완료 상태"),
        // 질문삭제
        QUESTION_DELETE(3, "질문 삭제 상태");

        private int num;
        private String message;

        QuestionStatus(int num, String message) {
            this.num = num;
            this.message = message;
        }
    }

    public enum SecretStatus {

        PUBLIC(1, "공개글 상태"),
        SECRET(2, "비밀글 상태");

        private int num;
        private String message;

        SecretStatus(int num, String message) {
            this.num = num;
            this.message = message;
        }
    }
}
