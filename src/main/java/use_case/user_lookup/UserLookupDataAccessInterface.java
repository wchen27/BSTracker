package use_case.user_lookup;	

import entity.User;

public interface UserLookupDataAccessInterface {

	User getUser(String tag);

}
