package use_case.user_lookup;	

import entity.User;

/*
 * The Data Interface for the user lookup
 */
public interface UserLookupDataAccessInterface {

	User getUser(String tag);

}
