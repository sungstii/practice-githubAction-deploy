package notice.board.post.service;

import lombok.RequiredArgsConstructor;
import notice.board.exception.BusinessLogicException;
import notice.board.exception.ExceptionCode;
import notice.board.post.entity.Post;
import notice.board.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private  final PostRepository postRepository;
    public Post createPost(Post post) {
        Post savePost = postRepository.save(post);

        return savePost;
    }

    public Post updatePost(Post post) {
        Post findPost = searchPostById(post.getPostId());

        Optional.ofNullable(post.getContent()).ifPresent(findPost ::setContent);
        Optional.ofNullable(post.getTitle()).ifPresent(findPost :: setTitle);
        Optional.ofNullable(post.getSecretStatus()).ifPresent(findPost :: setSecretStatus);

        Post updatePost = postRepository.save(findPost);
        return updatePost;
    }

    public Post findPost(Long postId) {
        Post findedPost = searchPostById(postId);
        //비밀글 거르기 (자기 아이디 + 공개글만) 권한 없는건 오류
        return findedPost;
    }

    public List<Post> findPosts() {
        List<Post> posts = postRepository.findAll();
        //비밀글 거르기 (자기 아이디 + 공개글만) 권한 없는건 오류
        return posts;
    }

    public void deletePost(long postId) {
        Post post = searchPostById(postId);
        post.setQuestionStatus(Post.QuestionStatus.QUESTION_DELETE);

        postRepository.save(post);
    }

    private Post searchPostById(Long postId) {
        Optional<Post> findPost = postRepository.findById(postId);
        Post post = findPost.orElseThrow(() -> new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));
        return post;
    }


}
