package SpringBoot;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    void deleteById(Long id);
}
