package assign09;

import java.text.DecimalFormat;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, but one that does a medium job of distributing students in a hash
 * table.
 * 
 * @author Erin Parker, Isabelle Cook, and Courtney Carroll
 * @version March 29, 2024
 */
public class StudentMediumHash {
	private int uid;
	private String firstName;
	private String lastName;

	/**
	 * Creates a new student with the specified uid, firstName, and lastName.
	 * 
	 * @param uid
	 * @param firstName
	 * @param lastName
	 */
	public StudentMediumHash(int uid, String firstName, String lastName) {
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the UID for this student object
	 */
	public int getUid() {
		return this.uid;
	}

	/**
	 * @return the first name for this student object
	 */
	
	public String getFirstName() {
		return this.firstName;
	}
 
	/**
	 * @return the last name for this student object
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @return true if this student and 'other' have the same UID, first name, and last name; false otherwise
	 */
	public boolean equals(Object other) {
		if(!(other instanceof StudentMediumHash))
			return false;

		StudentMediumHash rhs = (StudentMediumHash) other;

		return this.uid == rhs.uid && this.firstName.equals(rhs.firstName) && this.lastName.equals(rhs.lastName);
	}
	
	/**
	 * @return a textual representation of this student
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("0000000");
		return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
	}

	/**
	 * Generates a hash code based on the summed values of each 
	 * character in the student's first and last names.
	 * 
	 * @return integer hash code
	 */
	public int hashCode() {
		int hash = 0;
		
		for (int i = 0; i < firstName.length(); i++) {
			Character letter = firstName.charAt(i);
			hash = hash + letter.charValue();
		}
		
		for (int i = 0; i < lastName.length(); i++) {
			Character letter = lastName.charAt(i);
			hash = hash + letter.charValue();
		}
		
		return hash;
	}
	
}
