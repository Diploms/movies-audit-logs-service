package ua.karazin.moviesauditlogs.log;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface LogRepository extends ListCrudRepository<Log, String> {
  List<Log> findByProfileId(String profileId);
}
