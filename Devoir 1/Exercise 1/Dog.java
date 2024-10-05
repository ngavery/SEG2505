public class Dog extends Mammal{
    Dog() { //Constructeur non paramétré
        super(); //Appel au constructeur de la classe parente
        System.out.println("Dog Constructor called.");
    }

    Dog(String name) { //Constructeur paramétré
        super(name); //Appel au constructeur paramétré de la classe parente
        System.out.println("Dog Constructor with ma,e called.");
    }

    @Override
    public void makeSound() { //On implémente la méthode non implémentée de l'interface SoundMaker
        System.out.println("Woof!");
    }

    @Override
    String getAnimalType() { //On implémente la méthode abstraite de la classe Animal
        return "Dog";
    }
}
