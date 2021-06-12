package com.example.redditclone.repository;

import com.example.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {
}
