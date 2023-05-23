package animal_shelter.telegram_bot_for_animal_shelter.controller;

import animal_shelter.telegram_bot_for_animal_shelter.model.Pet;
import animal_shelter.telegram_bot_for_animal_shelter.model.Shelter;
import animal_shelter.telegram_bot_for_animal_shelter.model.enums.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetControllerTest {
    private Shelter shelter;
    private Pet pet1;
    private Pet pet2;
    private Pet pet3;
    private List<Pet> pets = new ArrayList<>();

    @BeforeEach
    void setUp() {
        shelter = new Shelter(1L, null, null, null, null, null, null, null, null);
        pet1 = new Pet(1L, 31, "Кот1", PetType.CAT, shelter);
        pet2 = new Pet(2L, 32, "Кот2", PetType.CAT, shelter);
        pet3 = new Pet(3L, 33, "Собака", PetType.DOG, shelter);

        pets = List.of(pet1, pet2, pet3);
    }

    @Test
    @DisplayName("When an object is created with parameters passed to it, then true")
    void createPet() {
        Pet petExpected = new Pet(1L, 31, "Кот1", PetType.CAT, shelter);
        assertEquals(petExpected, pet1);
    }

    @Test
    @DisplayName("When an object is update with parameters passed to it, then true")
    void updatePet() {
        Pet petExpected = new Pet(1L, 3, "Кот", PetType.CAT, shelter);
        pet1.setAgeInMonths(3);
        pet1.setName("Кот");
        assertEquals(petExpected, pet1);
    }

    @Test
    @DisplayName("When we find an object by petId, then true")
    void getPet() {
        Pet petExpected = null;
        for (Pet pet : pets) {
            if (pet.getPetId().equals(1L)) {
                petExpected = pet;
            }
        }
        assertEquals(petExpected, pet1);
    }

    @Test
    @DisplayName("When we find an object by PetType, then true")
    void getPetsByType() {
        List<Pet> petsExpected = List.of(pet1, pet2);
        List<Pet> petsActual = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getPetType().equals(PetType.CAT)) {
                petsActual.add(pet);
            }
        }
        assertEquals(petsExpected, petsActual);
    }

    @Test
    @DisplayName("When we find an object by petId, then delete object")
    void deletePet() {
        List<Pet> petsExpected = List.of(pet1, pet2);
        List<Pet> petsActual = new ArrayList<>(List.of(pet1, pet2, pet3));
        petsActual.removeIf(pet -> pet.getPetId().equals(3L));
        assertEquals(petsExpected, petsActual);
    }
}