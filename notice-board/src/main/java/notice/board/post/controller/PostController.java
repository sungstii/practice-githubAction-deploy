package notice.board.post.controller;

import lombok.RequiredArgsConstructor;
import notice.board.post.dto.PostPatchDto;
import notice.board.post.dto.PostPostDto;
import notice.board.post.entity.Post;
import notice.board.post.mapper.PostMapper;
import notice.board.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    // 게시글 등록 post
    private final PostMapper mapper;
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> postPost(@RequestBody PostPostDto postPostDto) {
        Post post = mapper.postPostToPost(postPostDto);
        Post createPost = postService.createPost(post);

        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }
    // 게시글 수정 patch
    @PatchMapping("/{post-id}")
    public ResponseEntity<Post> patchPost(@RequestBody PostPatchDto postPatchDto,
                          @PathVariable("post-id") long postId) {
        Post post = mapper.patchPostToPost(postPatchDto);
        post.setPostId(postId);
        Post updatePost = postService.updatePost(post);

        return new ResponseEntity<>(updatePost, HttpStatus.OK);

    }
    // 게시글 조회 get
    @GetMapping("/{post-id}")
    public ResponseEntity<Post> getPost(@PathVariable("post-id") long postId) {
        Post post = postService.findPost(postId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    // 게시글 전체 조회 getAll
    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.findPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    // 게시글 삭제 delete
    @DeleteMapping("/{post-id}")
    public void deletePost(@PathVariable("post-id") long postId) {
        postService.deletePost(postId);
    }
}
