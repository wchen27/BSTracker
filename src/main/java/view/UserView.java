package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.ViewManagerModel;
import interface_adapter.user_lookup.UserLookupState;
import interface_adapter.user_lookup.UserLookupViewModel;

public class UserView extends JPanel implements PropertyChangeListener {

    private final String viewName = "user lookup";
    private final UserLookupViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    private final JLabel title;
    private final JLabel userNameLabel;
    private final JLabel trophyCountLabel;
    private final JButton backButton;

    public UserView(UserLookupViewModel viewModel, ViewManagerModel viewManagerModel) {
        super();
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        viewModel.addPropertyChangeListener(this);

        title = new JLabel("User Lookup");
        title.setAlignmentX(CENTER_ALIGNMENT);

        userNameLabel = new JLabel("");
        userNameLabel.setAlignmentX(CENTER_ALIGNMENT);

        trophyCountLabel = new JLabel("");
        userNameLabel.setAlignmentX(CENTER_ALIGNMENT);

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
        this.add(userNameLabel);
        this.add(trophyCountLabel);
        this.add(backButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final UserLookupState state = (UserLookupState) evt.getNewValue();

        userNameLabel.setText(state.getUsername());

        trophyCountLabel.setText(String.valueOf(state.getTrophyCount()));
    }

    public String getViewName() {
        return viewName;
    }

}
