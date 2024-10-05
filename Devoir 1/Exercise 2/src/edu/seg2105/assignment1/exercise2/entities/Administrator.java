package edu.seg2105.assignment1.exercise2.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Administrator class represents an administrative employee with specific tasks.
 * Extends the Employee class.
 * 
 * @autor Hussein Al Osman
 */
public class Administrator extends Employee{

	// List of tasks assigned to the administrator
	List<String> tasks;

	/**
	 * Constructs a new Administrator with the given details.
	 * 
	 * @param firstName the first name of the administrator
	 * @param lastName the last name of the administrator
	 * @param id the ID of the administrator
	 * @param salary the salary of the administrator
	 */
	public Administrator(String firstName, String lastName, String id, double salary) {
		super(firstName, lastName, id, salary);
		tasks = new ArrayList<String>();
	}

	/**ÉCRIT PAR AVERY NG
	 * Ajoute une tâche à la liste des tâches de l'administrateur
	 * 
	 * @param task
	 * @return void
	 */
	public void addTask(String task) {
		tasks.add(task);
	}
	
	/**ÉCRIT PAR AVERY NG
	 * @return toutes les tâches dont l'adiminstrateur est chargé, chacune sur une ligne séparée avec un tiret
	 */
	public String getTaskList() {
		return String.join("\n\t - ",  tasks);
	}

    /**ÉCRIT PAR AVERY NG
     * @return the string representation of the administrator's information.
     */
    @Override
	public String toString() {

        return "Administrator information:\n"
                + "\tFirst name: " + getFirstName() + "\n"
                + "\tLast name: " + getLastName() + "\n"
                + "\tEmployee ID: " + getId() + "\n"
                + "\tSalary: " + getSalary() + "\n"
                + "\tTask list: " + "\n\t - " + getTaskList();		
	}
}
