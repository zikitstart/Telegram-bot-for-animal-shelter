package animal_shelter.telegram_bot_for_animal_shelter.service.impl;

import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.repository.PetRepository;
import animal_shelter.telegram_bot_for_animal_shelter.service.PetService;
import org.springframework.stereotype.Service;

@Service
// Класс по работе с питомцами
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Получение питомца по petId
    @Override
    public Pet getPet(Long petId){
        return petRepository.findPetByPetId(petId);
    }
}
