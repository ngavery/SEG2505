import java.util.ArrayList;
import java.util.Iterator;

public class AnimalTest {

    public static void main(String args[]) {

        //Instantiation des ArrayLists
        ArrayList<Animal> animals = new ArrayList<>(); 
        ArrayList<Mammal> mammals = new ArrayList<>(); 
        ArrayList<Bird> birds = new ArrayList<>(); 
        ArrayList<Dog> dogs = new ArrayList<>(); 
        ArrayList<Parrot> parrots = new ArrayList<>(); 

        //En utilisant une classe anonyme (dans laquelle les méthodes non implémentées sont implémentées), nous pouvons instancier la classe abstraite Animal
        Animal animal1 = new Animal() {
            @Override
            String getAnimalType() { //On implémente la méthode abstraite de la classe Animal
                return "Animal";
            }
            @Override
            public void makeSound() { //On implémente la méthode de l'interface SoundMaker
                System.out.println("Unknown animal sound");
            }
        };
        Animal animal2 = new Animal() {
            @Override
            String getAnimalType() {
                return "Animal";
            }
            @Override
            public void makeSound() {
                System.out.println("Unknown animal sound");
            }
        };
        Animal animal3 = new Animal("Tywin Lannister") {
            @Override
            String getAnimalType() {
                return "Animal";
            }
            @Override
            public void makeSound() {
                System.out.println("Named animal sound");
            }
        };
        Animal animal4 = new Animal("Walder Frey") {
            @Override
            String getAnimalType() {
                return "Animal";
            }
            @Override
            public void makeSound() {
                System.out.println("Named animal sound");
            }
        };

        //On ajoute les instanciations des animaux aux ArrayLists
        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);
        animals.add(animal4);

        mammals.add(new Mammal());
        mammals.add(new Mammal());
        mammals.add(new Mammal("Ser Bronn of The Blackwater"));
        mammals.add(new Mammal("Aegon Targaryen VI"));
        
        birds.add(new Bird());
        birds.add(new Bird());
        birds.add(new Bird("Olenna Tyrell"));
        birds.add(new Bird("Ser Sandor Clegane"));

        dogs.add(new Dog());
        dogs.add(new Dog());
        dogs.add(new Dog("Tyrion Lannister"));
        dogs.add(new Dog("Cersei Lannister"));

        parrots.add(new Parrot());
        parrots.add(new Parrot());
        parrots.add(new Parrot("Jon Snow"));
        parrots.add(new Parrot("Ned Stark"));

        //Iterators pour les quatres types d'animaux  
        Iterator<Animal> animalIterator = animals.iterator();
            System.out.println();
            System.out.println("Animals making sounds:");
            while (animalIterator.hasNext()) { //On parcourt les ArrayLists
                Animal animal = animalIterator.next();
                animal.makeSound(); 
            }      
        Iterator<Mammal> mammalIterator = mammals.iterator();
            System.out.println();
            System.out.println("Mammals making sounds:");
            while (mammalIterator.hasNext()) {
                Mammal mammal = mammalIterator.next();
                mammal.makeSound();
            }
        Iterator<Bird> birdIterator = birds.iterator();
            System.out.println();
            System.out.println("Birds making sounds:");
            while (birdIterator.hasNext()) {
                Bird bird = birdIterator.next();
                bird.makeSound();
            }    
        Iterator<Parrot> parrotIterator = parrots.iterator();
            System.out.println();
            System.out.println("Parrots making sounds:");
            while (parrotIterator.hasNext()) {
                Parrot parrot = parrotIterator.next();
                parrot.makeSound();
            }
        Iterator<Dog> dogIterator = dogs.iterator();
            System.out.println();
            System.out.println("Dogs making sounds:");
            while (dogIterator.hasNext()) {
                Dog dog = dogIterator.next();
                dog.makeSound();
            }
        
        //On affiche le nombre d'instantiations de chaque classe
        System.out.println();
        System.out.println("Total number of animals: " + Animal.getNumberOfAnimals());
        System.out.println("Total number of mammals: " + Mammal.getNumberOfMammals());
        System.out.println("Total number of birds: " + Bird.getNumberOfBirds());
        System.out.println();
    }
}
