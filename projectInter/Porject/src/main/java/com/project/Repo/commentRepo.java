package com.project.Repo;

import com.project.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Entities.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
public interface commentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByPostedByUser(Users commentTo);
}
