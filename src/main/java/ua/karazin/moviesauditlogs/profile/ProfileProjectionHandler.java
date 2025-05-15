package ua.karazin.moviesauditlogs.profile;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import ua.karazin.moviesbaseevents.membership.revision1.MembershipCreatedEvent1;
import ua.karazin.moviesbaseevents.moneyaccount.revision1.AccountCreatedEvent1;
import ua.karazin.moviesbaseevents.profiles.revision1.ProfileCreatedEvent1;

@Component
@RequiredArgsConstructor
public class ProfileProjectionHandler {
  private final ProfileRepository repository;

  @EventHandler
  private void on(ProfileCreatedEvent1 event) {
    repository.save(Profile.builder().profileId(event.id()).build());
  }

  @EventHandler
  private void on(AccountCreatedEvent1 event) {
    repository.findById(event.profileId())
        .map(profile -> {
          profile.setAccountId(event.accountId());
          return profile;
        })
        .map(repository::save)
        .orElseThrow();
  }

  @EventHandler
  private void on(MembershipCreatedEvent1 event) {
    var profile = repository.findById(event.profileId()).orElseThrow();

    profile.setMembershipId(event.membershipId());

    repository.save(profile);
  }
}
