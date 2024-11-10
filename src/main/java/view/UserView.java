package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.UserLookup.UserLookupViewModel;

public class UserView extends JPanel implements PropertyChangeListener {

    private final UserLookupViewModel viewModel;

    public UserView(UserLookupViewModel viewModel) {
        super();
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("User Lookup");
        title.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel userName = new JLabel("");
        userName.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel trophyCount = new JLabel("");
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
    
}
