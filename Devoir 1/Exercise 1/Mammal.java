public class Mammal extends Animal{
    static int numberOfMammals = 0;

    Mammal() { //Constructeur non paramétré
        super(); //Appel au constructeur de la classe parente
        numberOfMammals++;
        System.out.println("Mammal Constructor called.");
    }

    Mammal(String name){ //Constructeur paramétré
        super(name);
        numberOfMammals++;
        System.out.println("Mammal Constructor with name called.");
    }

    @Override
    public void makeSound() { //On implémente la méthode non implémentée de l'interface SoundMaker
        System.out.println("Mammal sound");
    }

    @Override
    String getAnimalType() { //On implémente la méthode abstraite de la classe Animal 
        return "Mammal";
    }

    public static int getNumberOfMammals() {
        return numberOfMammals;
    }
}
