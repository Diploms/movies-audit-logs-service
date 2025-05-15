package ua.karazin.moviesauditlogs.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
  Optional<Profile> findByMembershipId(String membershipId);
  Optional<Profile> findByAccountId(String accountId);
}
