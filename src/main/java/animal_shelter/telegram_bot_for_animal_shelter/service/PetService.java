package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;

public interface PetService {
    Pet getPet(Long petId);
}
