package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.Shelter;
import animal_shelter.telegram_bot_for_animal_shelter.model.Volunteer;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ShelterRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.ShelterService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ShelterServiceImpl implements ShelterService {
    private final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Map<Shelter, Set<Volunteer>> getAllSheltersAndTheirVolunteers() {
        List<Shelter> shelters = shelterRepository.findAll();

        Map<Shelter, Set<Volunteer>> map = new HashMap<>();

        for (Shelter shelter : shelters) {
            map.put(shelter, shelter.getVolunteerIds());
        }

        return map;
    }
}
