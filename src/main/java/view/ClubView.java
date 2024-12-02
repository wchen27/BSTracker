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

    private final JLabel title;
    private final JButton backButton;

    public ClubView(ClubLookupViewModel viewModel, ViewManagerModel viewManagerModel) {
        super();
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        viewModel.addPropertyChangeListener(this);

        title = new JLabel("Club Members");
        title.setAlignmentX(CENTER_ALIGNMENT);

        backButton = new JButton("Back");

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ClubLookupState state = (ClubLookupState) evt.getNewValue();
        final String subtitle;
        JPanel tableHeader = new JPanel();
        List<User> members = state.getMembers();
        if (members.isEmpty()) {
            subtitle = "No members found";
        } else {
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
                    viewManagerModel.setState("user");
                    viewManagerModel.firePropertyChanged();

                    // TODO: Popup user view in a new window

                    UserLookupViewModel userLookupViewModel = new UserLookupViewModel();
                    UserLookupOutputBoundary userLookupOutputBoundary = new UserLookupPresenter(userLookupViewModel,
                            viewManagerModel);

                    try {
                        final UserLookupOutputData userLookupOutputData = new UserLookupOutputData(member);
                        userLookupOutputBoundary.prepareSuccessView(userLookupOutputData);
                    } catch (RuntimeException error) {
                        userLookupOutputBoundary.prepareFailView(error.getMessage());
                    }
                }
            });
        }
    }

    public String getViewName() {
        return viewName;
    }
}
