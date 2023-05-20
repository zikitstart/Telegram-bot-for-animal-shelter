package animal_shelter.telegram_bot_for_animal_shelter.service;

import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;

import java.util.List;

public interface PetService {
    Pet createPet(Integer ageInMonths, String name, PetType petType);

    Pet updatePet(Long petId, Integer ageInMonths, String name);

    List<Pet> getPetsByTypes(PetType petType);

    Pet getPet(Long petId);

    Pet getPetByParameters(String name, PetType petType);

    Pet deletePet(Long petId);
}
