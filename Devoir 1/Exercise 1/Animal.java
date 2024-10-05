abstract class Animal implements SoundMaker{ //Implémente SoundMaker, alors toutes les sous-classe de Animal doivent implémenter makeSound()
    
    static int numberOfAnimals = 0; 
    
    String name;
    
    Animal() { //Constructeur non paramétré
        numberOfAnimals++;
        System.out.println("Animal Constructor called.");
        name = "Unknown animal";
    }

    Animal(String name) { //Constructeur paramétré
        numberOfAnimals++;
        System.out.println("Animal Constructor with name called.");
        this.name = name;
    }

    abstract String getAnimalType(); //Méthode abstraite qui doit être implémentée dans les sous-classes

    static int getNumberOfAnimals() {
        return numberOfAnimals;
    }
}
