package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.Shelter;
import animal_shelter.telegram_bot_for_animal_shelter.model.Volunteer;

import java.util.Map;
import java.util.Set;

public interface ShelterService {
    Map<Shelter, Set<Volunteer>> getAllSheltersAndTheirVolunteers();
}
