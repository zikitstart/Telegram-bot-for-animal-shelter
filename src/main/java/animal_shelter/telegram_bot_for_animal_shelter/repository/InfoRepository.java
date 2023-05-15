package animal_shelter.telegram_bot_for_animal_shelter.repository;

import animal_shelter.telegram_bot_for_animal_shelter.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {

    // Получение информации по infoId
    Info getInfoByInfoId(Long id);
}
