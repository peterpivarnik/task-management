package com.pp.js.tm.testservice;

import com.pp.js.tm.entity.Bug;
import com.pp.js.tm.repository.BugRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TestBugService {

  private final BugRepository bugRepository;

  public TestBugService(BugRepository bugRepository) {
    this.bugRepository = bugRepository;
  }

  public void deleteAll() {
    bugRepository.deleteAll();
  }

  public Bug createBug() {
    Bug bug = getBug();
    return bugRepository.save(bug);
  }

  private Bug getBug() {
    Bug bug = new Bug();
    bug.setSteps("steps");
    bug.setType("bug");
    bug.setName("name");
    bug.setCreatedAt(Instant.now());
    bug.setSeverity("Normal");
    return bug;
  }

  public List<Bug> findAll() {
    return bugRepository.findAll();
  }
}
