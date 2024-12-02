package use_case.previous_search;	

import entity.User;

/*
 * The Data Access Interface to allow previous data to get and add the data it needs
 */
public interface PreviousSearchDataAccessInterface {

	String[] getPreviousSearches();

	void addSearch(String search);

}
