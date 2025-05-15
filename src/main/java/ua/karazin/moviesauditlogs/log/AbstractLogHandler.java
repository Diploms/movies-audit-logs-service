package ua.karazin.moviesauditlogs.log;

import lombok.RequiredArgsConstructor;
import ua.karazin.moviesauditlogs.profile.ProfileService;

@RequiredArgsConstructor
public abstract class AbstractLogHandler {
  protected final LogRepository logRepository;
  protected final ProfileService profileService;

  protected void save(Log log) {
    logRepository.save(log);
  }
}
