package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable Long postId) {
        return this.commentRepository.findByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable Long postId, @PathVariable Long commentId) {
        return this.commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(path = "/{postId}/comments")
    public void createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        Comment commentNew = new Comment();
        commentNew.setContent(comment.getContent());
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        commentNew.setPost(post);
        this.commentRepository.save(commentNew);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public void updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment comment) throws JsonProcessingException {
        Comment actualComment = this.commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        actualComment.setContent(comment.getContent());
        this.commentRepository.save(actualComment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        Comment comment = this.commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        this.commentRepository.delete(comment);
    }
    // END
}
