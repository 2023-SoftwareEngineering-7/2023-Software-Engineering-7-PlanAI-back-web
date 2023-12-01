package com.softwareengineering.planai;

import com.softwareengineering.planai.domain.entity.Comment;
import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.domain.entity.User;
import com.softwareengineering.planai.web.dto.request.ScheduleRegisterDto;
import com.softwareengineering.planai.web.service.CommentService;
import com.softwareengineering.planai.web.service.PostService;
import com.softwareengineering.planai.web.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlanaiApplicationTests {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {

        User user =User.builder()
                .name("adsd")
                .email("sadad")
                .phoneNumber("asdasd")
                .build();
        userService.addUser(user);

        Post post = Post.builder()
                .title("ssss")
                .content("safas")
                .author(user)
                .build();

        postService.addPost(post);

        commentService.addComment(Comment.builder()
                .author(user)
                .post(post)
                .content("sib")
                .build());

        Post pst = postService.findById(1L).get();

    }

}
