package interface_adapter.user_lookup;

import interface_adapter.ViewModel;

public class UserLookupViewModel extends ViewModel<UserLookupState> {
    public UserLookupViewModel() {
        super("user lookup");
        this.setState(new UserLookupState());
    }
}
