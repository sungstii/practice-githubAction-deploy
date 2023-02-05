package notice.board.post.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import notice.board.member.entity.Member;
import notice.board.post.entity.Post;


@Getter
@RequiredArgsConstructor
public class PostPatchDto {

    private Long postId;
    private Long memberId;
    private String title;
    private String content;
    private Post.SecretStatus secretStatus;
}
