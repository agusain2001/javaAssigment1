package com.project.Controller;

import com.project.Entities.Comment;
import com.project.Entities.commentReq;

import com.project.Entities.commentReq;
import com.project.implementents.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class homeController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestBody commentReq request) {
        String response = commentService.addComment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{commentTo}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String commentTo) {
        List<Comment> comments = commentService.getCommentsForUser(commentTo);
        return ResponseEntity.ok(comments);
    }
}
