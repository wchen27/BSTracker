package view;

import app.UserLookupUseCaseFactory;
import data_access.APIDataAccessObject;
import entity.ClubFactory;
import entity.MatchFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.club_lookup.ClubLookupState;
import interface_adapter.club_lookup.ClubLookupViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.user_lookup.UserLookupController;
import interface_adapter.user_lookup.UserLookupPresenter;
import interface_adapter.user_lookup.UserLookupViewModel;
import use_case.user_lookup.UserLookupDataAccessInterface;
import use_case.user_lookup.UserLookupInteractor;
import use_case.user_lookup.UserLookupOutputBoundary;
import use_case.user_lookup.UserLookupOutputData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ClubView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Club lookup";
    private final ClubLookupViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final UserLookupController userLookupController;

    private final JLabel title = new JLabel("Club Members");;
    private final JButton backButton = new JButton("Back");;

    public ClubView(ClubLookupViewModel viewModel, ViewManagerModel viewManagerModel,
                    UserLookupController userLookupController) {
        super();
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.userLookupController = userLookupController;
        viewModel.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ClubLookupState state = (ClubLookupState) evt.getNewValue();
        final String subtitle;
        List<User> members = state.getMembers();
        if (members.isEmpty()) {
            subtitle = "No members found";
        } else {
            // clear view
            this.removeAll();

            title.setAlignmentX(CENTER_ALIGNMENT);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    viewManagerModel.setState("search");
                    viewManagerModel.firePropertyChanged();
                }
            });

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            this.add(title);
            this.add(backButton);
        }

        for (User member : members) {
            JPanel row = new JPanel();
            JLabel memberTrophies = new JLabel(String.valueOf(member.getTrophies()));
            JButton memberButton = new JButton(member.getUsername());
            row.add(memberTrophies);
            row.add(memberButton);
            this.add(row);

            memberButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    viewManagerModel.setState("user lookup");
                    viewManagerModel.firePropertyChanged();

                    userLookupController.execute(member.getTag());
                }
            });
        }
    }

    public String getViewName() {
        return viewName;
    }
}
