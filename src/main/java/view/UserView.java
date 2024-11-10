package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.file.AccessDeniedException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.UserLookup.UserLookupViewModel;

public class UserView extends JPanel implements PropertyChangeListener {

    private final UserLookupViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public UserView(UserLookupViewModel viewModel) {
        super();
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("User Lookup");
        title.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel userName = new JLabel("");
        userName.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel trophyCount = new JLabel("");
        userName.setAlignmentX(CENTER_ALIGNMENT);

        final JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }});

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userName);
        this.add(trophyCount);
        this.add(backButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        
    }
    
}
