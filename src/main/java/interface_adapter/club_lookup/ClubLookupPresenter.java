package interface_adapter.club_lookup;

import entity.Club;
import interface_adapter.ViewManagerModel;
import use_case.club_lookup.ClubLookupOutputBoundary;
import use_case.club_lookup.ClubLookupOutputData;

import javax.swing.*;
import java.sql.SQLOutput;

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
        System.out.println("Club tag exists!");
    }

    public void prepareFailView(String errorMessage) {
        JOptionPane.showMessageDialog(null, "The club tag you entered does not exist. Please try again! \n" + errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println("Club tag does not exist!");
    }
}
