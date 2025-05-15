package ua.karazin.moviesauditlogs.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
  private final ProfileRepository profileRepository;

  public String getProfileIdBy(String membershipId) {
    return profileRepository.findByMembershipId(membershipId).orElseThrow().getProfileId();
  }
}
