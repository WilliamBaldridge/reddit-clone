package com.example.redditclone.service;

import com.example.redditclone.dto.SubredditDto;
import com.example.redditclone.model.Subreddit;
import com.example.redditclone.repository.SubredditRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepo subredditRepo;
    private final AuthenticationService authenticationService;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepo.save(mapToSubredditDto(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;
    }

    private Subreddit mapToSubredditDto(SubredditDto subredditDto) {

        return Subreddit.builder().name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .build();
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder().name(subreddit.getName())
                .id(subreddit.getId())
                .numberOfPosts(subreddit.getPosts().size())
                .build();
    }
}
