package SpringBoot;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * The Buddy Repository - Interface
 * @author Muneeb Nasir
 * @version 4806.5
 */

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    void deleteById(Long id);
}
