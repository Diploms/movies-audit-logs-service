package ua.karazin.moviesauditlogs.log;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.karazin.moviesauditlogs.profile.ProfileRepository;

@RestController
@RequiredArgsConstructor
public class LogController {
  private final ProfileRepository profileRepository;
  private final LogRepository logRepository;

  @GetMapping("/{id}")
  public Iterable<Log> getLogsForProfile(@PathVariable @NotBlank String id) {
    profileRepository.findByMembershipId(id).orElseThrow();
    return logRepository.findByProfileId(id);
  }
}
