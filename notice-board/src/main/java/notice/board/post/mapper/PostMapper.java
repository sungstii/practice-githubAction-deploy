package notice.board.post.mapper;

import notice.board.member.entity.Member;
import notice.board.post.dto.PostPatchDto;
import notice.board.post.dto.PostPostDto;
import notice.board.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    default Post postPostToPost(PostPostDto postDto) {
        Post post = new Post();
        Member member = new Member();
        member.setMemberId(postDto.getMemberId());

        post.setMember(member);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return post;
    }
    Post patchPostToPost(PostPatchDto patchDto);
}
