package view;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.club_lookup.ClubLookupState;
import interface_adapter.club_lookup.ClubLookupViewModel;

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
            subtitle = "No data found";
        } else {
            subtitle = "Members of the club:"; // Add name of the club
        }
        tableHeader.add(new JLabel(subtitle));
        this.add(tableHeader);

        for (User member : members) {
            JPanel row = new JPanel();
            JLabel memberName = new JLabel(member.getUsername());
            row.add(memberName);
            this.add(row);
        }
    }

    public String getViewName() {
        return viewName;
    }
}
