package com.pp.js.tm.testservice;

import com.pp.js.tm.repository.BugRepository;
import jakarta.transaction.Transactional;
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
}
