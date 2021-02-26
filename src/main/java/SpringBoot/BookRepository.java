package SpringBoot;

import org.springframework.data.repository.CrudRepository;
import java.util.Collection;

/**
 * The Address Book Store Interface - Provides Headers for Queries
 * @author Muneeb Nasir
 * @version 4806.6
 */

public interface BookRepository extends CrudRepository<AddressBook, Long> {
    AddressBook findAddressBookById(Long id);

    Collection<AddressBook> findAll();
}
