package com.pp.js.tm.service;

import static java.time.ZoneOffset.UTC;

import com.pp.js.tm.entity.Bug;
import com.pp.js.tm.repository.BugRepository;
import com.pp.js.tm.service.dto.BugResponseDto;
import com.pp.js.tm.service.dto.CreateBugDto;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

/**
 * Service providing extended functionality for {@link BugRepository}.
 */
@Service
@Transactional
public class BugService {

  private final BugRepository bugRepository;

  public BugService(BugRepository bugRepository) {
    this.bugRepository = bugRepository;
  }

  /**
   * Creates bug.
   *
   * @param createBugDto request containing info about bug creation
   * @return Created bug.
   */
  public BugResponseDto createBug(CreateBugDto createBugDto) {
    Bug bug = getBug(createBugDto);
    Bug savedBug = bugRepository.save(bug);
    return mapToBugResponseDto(savedBug);
  }

  private Bug getBug(CreateBugDto createBugDto) {
    Bug bug = new Bug();
    bug.setSeverity(createBugDto.getSeverity());
    bug.setSteps(createBugDto.getSteps());
    bug.setName(createBugDto.getName());
    bug.setCreatedAt(Instant.now());
    bug.setType("bug");
    return bug;
  }

  private BugResponseDto mapToBugResponseDto(Bug savedBug) {
    BugResponseDto response = new BugResponseDto();
    response.setName(savedBug.getName());
    response.setSeverity(savedBug.getSeverity());
    response.setCreatedAt(LocalDateTime.ofInstant(savedBug.getCreatedAt(), UTC));
    response.setUid(savedBug.getUid());
    response.setSteps(savedBug.getSteps());
    return response;
  }
}
