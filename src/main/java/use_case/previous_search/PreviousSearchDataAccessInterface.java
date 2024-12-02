package use_case.previous_search;	

import entity.User;

public interface PreviousSearchDataAccessInterface {

	String[] getPreviousSearches();

	void addSearch(String search);

}
