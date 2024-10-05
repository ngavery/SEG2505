public class Parrot extends Bird {
    Parrot() { //Constructeur non paramétré
        super(); //Appel au constructeur de la classe parente
        System.out.println("Parrot Constructor called.");
    }

    Parrot(String name) { //Constructeur paramétré
        super(name);
        System.out.println("Parrot Constructor with name called.");
    }

    @Override
    public void makeSound() { //On implémente la méthode non implémentée de l'interface SoundMaker 
        System.out.println("Squawk!");
    }

    @Override
    String getAnimalType() { //On implémente la méthode abstraite de la classe Animal
        return "Parrot";
    }
}
