package animal_shelter.telegram_bot_for_animal_shelter.repository;

import animal_shelter.telegram_bot_for_animal_shelter.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContextRepository extends JpaRepository<UserDetails, Long> {

}
