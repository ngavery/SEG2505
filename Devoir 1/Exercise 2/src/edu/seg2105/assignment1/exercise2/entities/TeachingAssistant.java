//ÉCRIT PAR AVERY NG, cette classe est basée sur la classe Professor, alors plusieurs lignes de code sont les mêmes.

package edu.seg2105.assignment1.exercise2.entities;

public class TeachingAssistant extends Instructor {

	private final int max_courses = 3; //Nombre maximum de cours auquel un TA pourrait être assigné.

	/**
	 * Constructeur pour un nouveau TA avec les attributs suivant:
	 *
	 * @param firstName 
	 * @param lastName
	 * @param id 
	 * @param salary 
	 */
	public TeachingAssistant(String firstName, String lastName, String id, double salary) {
		super(firstName, lastName, id, salary); //Appel au constructeur de la classe Instructor --> Employee --> Person
	}

	/**
	 * Affecte l'assistant à un cours si son nombre de cours maximum n'est pas dépassé.
	 * 
	 * @param course 
	 * @return true si l'assistant à été ajouté, false si l'assistant est déjà assigné au nombre maximum de cours
	 */
	@Override
	public boolean assignCourse(Course course) {
		if (courses.size() < max_courses) {
			courses.add(course);
			return true;
		} else {
			return false;
		}
	}
	/**
     * Returns a string representation of the TA's information.
     * I added this method in order to display "TA information", distinguishing itself from the toString() method from the Intructor class which would have been called otherwise
     * @return the string representation of the TA
     */
    @Override
    public String toString() {
    	
        return "TA information:\n"
                + "\tFirst name: " + getFirstName() + "\n"
                + "\tLast name: " + getLastName() + "\n"
                + "\tEmployee ID: " + getId() + "\n"
                + "\tSalary: " + getSalary() + "\n"
                + (courses.size() > 0 ? "\tList of assigned courses:" + getCoursesTable() : "");   
    }
}
