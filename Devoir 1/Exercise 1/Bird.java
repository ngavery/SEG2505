public class Bird extends Animal{
    
    static int numberOfBirds = 0;

    Bird() { //Constructeur non paramétré
        super();  //Appel au constructeur de la classe parente
        numberOfBirds++;
        System.out.println("Bird Constructor called.");
    }

    Bird(String name) {  //Constructeur paramétré
        super(name);  //Appel au constructeur paramétré de la classe parente
        numberOfBirds++;
        System.out.println("Bird Constructor with name callled.");
    }

    @Override
    public void makeSound() {
        System.out.println("Bird sound");
    }

    @Override
    String getAnimalType() {
        return "Bird";
    }

    public static int getNumberOfBirds() {
        return numberOfBirds;
    }
}
