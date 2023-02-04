package notice.board.post.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import notice.board.member.entity.Member;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
