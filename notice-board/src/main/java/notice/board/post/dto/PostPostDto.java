package notice.board.post.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import notice.board.member.entity.Member;


@Getter
@RequiredArgsConstructor
public class PostPostDto {

    private Long postId;
    private Long memberId;
    private String title;
    private String content;
}
