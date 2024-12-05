package org.softwaretechnologies;

import org.softwaretechnologies.animals.*;

public class AnimalFactory {

    /**
     * Метод для создания животных по типу
     * @param name - имя животного
     * @param type - тип животного
     * @return животное, соответствующее каждому из типов.
     */


    public static Animal createAnimal(String name, AnimalType type) {
        /* TODO в зависимости от type создайте и верните нужное животное */
        if (type == AnimalType.CAT){
            Animal animal = new Cat(name);
            return animal;
        }
        if (type == AnimalType.DOG){
            Animal animal = new Dog(name);
            return animal;
        }
        if (type == AnimalType.COW){
            Animal animal = new Cow(name);
            return animal;
        }
        return null;
    }
}
