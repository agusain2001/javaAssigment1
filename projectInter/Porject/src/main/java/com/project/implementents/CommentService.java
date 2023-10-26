package com.project.implementents;

import com.project.Entities.Comment;
import com.project.Entities.Users;
import com.project.Entities.commentReq;
import com.project.Repo.UserRepo;
import com.project.Repo.commentRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private commentRepo commentRepository;

    @Transactional
    public String addComment(commentReq request) {
        String commentFrom = request.getCommentFrom();
        String commentTo = request.getCommentTo();
        String message = request.getMessage();

        // Validate the request parameters and process the comment addition
        if (isValidRequest(request)) {
            // Check if both 'commentFrom' and 'commentTo' users exist
            List<Users> fromUsers = userRepository.findByCommentFrom(commentFrom);
            List<Users> toUsers = userRepository.findByCommentTo(commentTo);
            Users fromUser = null;
            Users toUser = null;
            if (fromUsers.isEmpty() || toUsers.isEmpty()) {
                fromUser = new Users();
                fromUser.setCommentFrom(commentFrom);
                fromUser.setCommentTo(commentTo);
                userRepository.save(fromUser);
            } else {
                // Assuming there is only one user with the given commentFrom

                fromUser.setCommentTo(commentTo);
                userRepository.save(fromUser);
            }



            // Create a new Comment
            Comment comment = new Comment();
            comment.setMessage(message);
            comment.setCommentDateTime(LocalDateTime.now());
            comment.setPostedByUser(fromUser!=null?fromUser:fromUsers.get(0));

            // Save the comment to the database
            commentRepository.save(comment);

            return "Comment added successfully";
        }

        // If any validation or database checks fail, return an error message
        return "Invalid Request";
    }

    private boolean isValidRequest(commentReq request) {
        return request != null && isValidUsername(request.getCommentFrom()) && isValidUsername(request.getCommentTo()) && isValidMessage(request.getMessage());
    }

    private boolean isValidUsername(String username) {
        return username != null && !username.isEmpty() && username.matches("^[a-zA-Z]+$");
    }

    private boolean isValidMessage(String message) {
        return message != null && !message.isEmpty();
    }


    public List<Comment> getCommentsForUser(String commentTo) {
        List<Users> users = userRepository.findByCommentTo(commentTo);

        if (!users.isEmpty()) {
            List<Comment> comments = new ArrayList<>();
            for (Users user : users) {
                // Retrieve comments for the specific user
                List<Comment> userComments = commentRepository.findByPostedByUser(user);

                // Filter and add only the comments where commentTo matches
                userComments = userComments.stream()
                        .filter(comment -> comment.getPostedByUser().getCommentTo().equals(commentTo))
                        .collect(Collectors.toList());

                comments.addAll(userComments);
            }
            return comments;
        } else {
            return Collections.emptyList();
        }
    }




}
