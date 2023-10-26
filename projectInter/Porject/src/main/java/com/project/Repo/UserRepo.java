package com.project.Repo;

import com.project.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    List<Users> findByCommentTo(String commentTo);

    List<Users> findByCommentFrom(String commentFrom);
}
