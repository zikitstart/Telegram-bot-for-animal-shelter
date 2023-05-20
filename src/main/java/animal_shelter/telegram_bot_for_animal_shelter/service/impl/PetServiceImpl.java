package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.Shelter;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import animal_shelter.telegram_bot_for_animal_shelter.repository.PetRepository;
import animal_shelter.telegram_bot_for_animal_shelter.repository.ShelterRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Класс по работе с питомцами
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;

    public PetServiceImpl(PetRepository petRepository, ShelterRepository shelterRepository) {
        this.petRepository = petRepository;
        this.shelterRepository = shelterRepository;
    }

    //Создание питомца
    @Override
    public Pet createPet(Integer ageInMonths, String name, PetType petType) {
        long shelterId;
        if (petType.equals(PetType.CAT)) {
            shelterId = 1L;
        } else {
            shelterId = 2L;
        }
        Shelter shelter = shelterRepository.getSheltersByShelterId(shelterId);
        Pet pet = new Pet(ageInMonths, name, petType, shelter);
        return petRepository.save(pet);
    }

    //Обновление питомца
    @Override
    public Pet updatePet(Long petId, Integer ageInMonths, String name) {
        Pet pet = getPet(petId);
        pet.setAgeInMonths(ageInMonths);
        pet.setName(name);
        return petRepository.save(pet);
    }

    //Получение питомцев по petType
    @Override
    public List<Pet> getPetsByTypes(PetType petType) {
        return petRepository.findPetsByPetType(petType);
    }

    //Получение питомца по petId
    @Override
    public Pet getPet(Long petId) {
        return petRepository.findPetByPetId(petId);
    }

    //Получение питомца по параметрам
    @Override
    public Pet getPetByParameters(String name, PetType petType) {
        return petRepository.findPetByNameAndPetType(name, petType);
    }

    //Удаление питомца по petId
    @Override
    public Pet deletePet(Long petId) {
        Pet pet = petRepository.findPetByPetId(petId);
        petRepository.delete(pet);
        return pet;
    }
}
