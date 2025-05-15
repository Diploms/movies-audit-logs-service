package ua.karazin.moviesauditlogs.log;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;
import ua.karazin.moviesauditlogs.profile.ProfileService;
import ua.karazin.moviesauditlogs.utils.LocalDateTimeUtils;
import ua.karazin.moviesbaseevents.membership.revision1.MembershipActivatedEvent1;
import ua.karazin.moviesbaseevents.membership.revision1.MembershipCreatedEvent1;
import ua.karazin.moviesbaseevents.membership.revision1.MembershipRejectedEvent1;
import ua.karazin.moviesbaseevents.membership.revision1.MembershipRenewedEvent1;
import ua.karazin.moviesbaseevents.profiles.revision1.ProfileConfirmedEvent1;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component
public class LogMembershipHandler extends AbstractLogHandler {
  public LogMembershipHandler(LogRepository logRepository, ProfileService profileService) {
    super(logRepository, profileService);
  }

  @EventHandler
  private void on(ProfileConfirmedEvent1 event) {
    save(Log.builder()
        .action("Profile create")
        .profileId(event.profileId())
        .level(LogLevel.INFO)
        .build());
  }

  @EventHandler
  private void on(MembershipCreatedEvent1 event, @Timestamp Instant timestamp) {
    save(Log.builder()
        .action("Membership create")
        .profileId(event.profileId())
        .level(LogLevel.INFO)
        .createdAt(LocalDateTimeUtils.from(timestamp))
        .build());
  }

  @EventHandler
  private void on(MembershipRenewedEvent1 event, @Timestamp Instant timestamp) {
    var profileId = profileService.getProfileIdBy(event.membershipId());
    save(Log.builder()
        .action("Membership renewed")
        .profileId(profileId)
        .level(LogLevel.INFO)
        .createdAt(LocalDateTimeUtils.from(timestamp))
        .build());
  }

  @EventHandler
  private void on(MembershipActivatedEvent1 event, @Timestamp Instant timestamp) {
    var profileId = profileService.getProfileIdBy(event.membershipId());
    save(Log.builder()
        .action("Membership activated")
        .profileId(profileId)
        .level(LogLevel.INFO)
        .createdAt(LocalDateTimeUtils.from(timestamp))
        .build());
  }

  @EventHandler
  private void on(MembershipRejectedEvent1 event, @Timestamp Instant timestamp) {
    var profileId = profileService.getProfileIdBy(event.membershipId());
    save(Log.builder()
        .action("Membership rejected")
        .profileId(profileId)
        .level(LogLevel.WARNING)
        .createdAt(LocalDateTimeUtils.from(timestamp))
        .build());
  }
}
