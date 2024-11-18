package interface_adapter.club_lookup;

import entity.Club;
import interface_adapter.ViewManagerModel;
import use_case.club_lookup.ClubLookupOutputBoundary;
import use_case.club_lookup.ClubLookupOutputData;

public class ClubLookupPresenter implements ClubLookupOutputBoundary {

    private final ClubLookupViewModel ClubLookupViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubLookupPresenter(ClubLookupViewModel ClubLookupViewModel,
                                      ViewManagerModel viewManagerModel) {
        this.ClubLookupViewModel = ClubLookupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(ClubLookupOutputData ClubLookupOutputData) {
        final ClubLookupState state = ClubLookupViewModel.getState();
        state.setMembers(ClubLookupOutputData.getMembers());

        ClubLookupViewModel.firePropertyChanged();
        this.viewManagerModel.setState(ClubLookupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String errorMessage) {
        throw new UnsupportedOperationException(errorMessage);
    }
}
