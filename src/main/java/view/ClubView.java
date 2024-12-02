package view;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.club_lookup.ClubLookupState;
import interface_adapter.club_lookup.ClubLookupViewModel;
import interface_adapter.user_lookup.UserLookupController;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ClubView extends JPanel implements PropertyChangeListener {

    private final ClubLookupViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final UserLookupController userLookupController;

    private final JLabel title = new JLabel("Club Members");
    private final JButton backButton = new JButton("Back");

    public ClubView(ClubLookupViewModel viewModel, ViewManagerModel viewManagerModel,
                    UserLookupController userLookupController) {
        super();

        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.userLookupController = userLookupController;
        viewModel.addPropertyChangeListener(this);

        title.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setAlignmentX(CENTER_ALIGNMENT);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ClubLookupState state = (ClubLookupState) evt.getNewValue();
        List<User> members = state.getMembers();
        if (members.isEmpty()) {
            System.out.println("No members found");
        } else {
            // clear view
            this.removeAll();

            title.setAlignmentX(CENTER_ALIGNMENT);
            backButton.addActionListener(e -> {
                viewManagerModel.setState("search");
                viewManagerModel.firePropertyChanged();
            });

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            this.add(title);
            this.add(backButton);
        }

        JPanel topLabels = new JPanel();
        topLabels.setLayout(new BoxLayout(topLabels, BoxLayout.X_AXIS));
        topLabels.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel trophiesLabel = new JLabel("Trophy Count");
        trophiesLabel.setFont(trophiesLabel.getFont().deriveFont(1));
        trophiesLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JLabel memberLabel = new JLabel("Member Username");
        memberLabel.setFont(trophiesLabel.getFont().deriveFont(1));
        memberLabel.setAlignmentX(CENTER_ALIGNMENT);

        topLabels.add(trophiesLabel);
        topLabels.add(memberLabel);
        this.add(topLabels);

        for (User member : members) {
            JPanel row = new JPanel();
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
            row.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel memberTrophies = new JLabel(String.valueOf(member.getTrophies()));
            memberTrophies.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

            JButton memberButton = new JButton(member.getUsername());
            memberButton.setAlignmentX(CENTER_ALIGNMENT);

            row.add(memberTrophies);
            row.add(memberButton);
            this.add(row);

            memberButton.addActionListener(e -> {
                viewManagerModel.setState("user lookup");
                viewManagerModel.firePropertyChanged();

                userLookupController.execute(member.getTag());
            });
        }
    }

    public String getViewName() {
        return "Club lookup";
    }
}
