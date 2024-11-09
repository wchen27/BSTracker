package use_case.UserLookup;

import entity.User;

public interface UserLookupDataAccessInterface {

	User getUser(String tag);

}
