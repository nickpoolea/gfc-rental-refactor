package com.libertymutual.spark.myapp.utilities;

import org.mindrot.jbcrypt.BCrypt;

import com.libertymutual.spark.myapp.models.Apartment;
import com.libertymutual.spark.myapp.models.ApartmentsUsers;
import com.libertymutual.spark.myapp.models.User;

public class SeedData {
	
	public static void create() {
		
		
		String password = "password";
		String encrytedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		
		try (AutoCloseableDb db = new AutoCloseableDb()) {
			User.deleteAll();
			Apartment.deleteAll();
			ApartmentsUsers.deleteAll();
			

			User user = new User("Bart@gmail.com", encrytedPassword, "Bart", "Simpson");
			user.saveIt();
			user = new User("Homer@gmail.com", encrytedPassword, "Homer", "Simpson");
			user.saveIt();
			
			Apartment apartment = new Apartment(2000, 3, 1, 1000, "123 Main", "Honolulu", "Hi", 96825, true);
			user.add(apartment);
			apartment.saveIt();
			apartment = new Apartment(2500, 3, 2, 2000, "721 Evergreen", "Honolulu", "Hi", 96825, true);
			user.add(apartment);
			apartment.saveIt();
			
			user = new User("Marge@gmail.com", encrytedPassword, "Marge", "Simpson");
			user.saveIt();
			user = new User("Lisa@gmail.com", encrytedPassword, "Lisa", "Simpson");
			user.saveIt();
			
			apartment = new Apartment(2500, 3, 2, 2000, "458 Kaiser", "Honolulu", "Hi", 96825, true);
			user.add(apartment);
			apartment.saveIt();
			apartment = new Apartment(1500, 2, 2, 750, "123 Koko Isle", "Honolulu", "Hi", 96825, true);
			user.add(apartment);
			apartment.saveIt();
			
			
		}
		
	}

}
