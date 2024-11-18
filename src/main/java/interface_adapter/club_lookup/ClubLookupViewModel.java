package interface_adapter.club_lookup;

import interface_adapter.ViewModel;

public class ClubLookupViewModel extends ViewModel<ClubLookupState> {

    public ClubLookupViewModel() {
        super("Club lookup");
        this.setState(new ClubLookupState());
    }

}
