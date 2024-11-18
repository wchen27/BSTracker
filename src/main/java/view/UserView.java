package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import interface_adapter.ViewManagerModel;
import interface_adapter.user_lookup.UserLookupState;
import interface_adapter.user_lookup.UserLookupViewModel;

public class UserView extends JPanel implements PropertyChangeListener {

    private final String viewName = "user lookup";
    private final UserLookupViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    // TODO implement a club for the user
    private final JLabel title;
    private final JLabel tagLabel;
    private final JLabel userNameLabel;
    private final JLabel trophiesLabel;
    private final JLabel highestTrophiesLabel;
    private final JLabel trioVictoriesLabel;
    private final JLabel duoVictoriesLabel;
    private final JLabel soloVictoriesLabel;
    private final JPanel brawlerPanel;
    private final JPanel matchPanel;
    private final JScrollPane brawlerScrollPane;
    private final JScrollPane matchScrollPane;
    private final JButton backButton;

    public UserView(UserLookupViewModel viewModel, ViewManagerModel viewManagerModel) {
        super();
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        viewModel.addPropertyChangeListener(this);

        title = new JLabel("User Lookup");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(20f));

        tagLabel = new JLabel("");

        userNameLabel = new JLabel("");

        trophiesLabel = new JLabel("");

        highestTrophiesLabel = new JLabel("");

        trioVictoriesLabel = new JLabel("");

        duoVictoriesLabel = new JLabel("");

        soloVictoriesLabel = new JLabel("");
        
        brawlerPanel = new JPanel();
        brawlerPanel.setLayout(new BoxLayout(brawlerPanel, BoxLayout.X_AXIS));
        matchPanel = new JPanel();
        matchPanel.setLayout(new BoxLayout(matchPanel, BoxLayout.X_AXIS));

        //TODO Make this a bit better and editiable
        brawlerScrollPane = new JScrollPane();
        
        matchScrollPane = new JScrollPane();
        

        backButton = new JButton("Back");

        // Set up panels for a nicer overall look
        JPanel userAccountPanel = new JPanel();
        userAccountPanel.setLayout(new BoxLayout(userAccountPanel, BoxLayout.Y_AXIS));
        userAccountPanel.setBorder(new EmptyBorder(20,20,20,10));
        userAccountPanel.add(userNameLabel);
        userAccountPanel.add(tagLabel);
        // TODO club should be added here
        // userAccountPanel.add(clubPanel);

        JPanel victoriesPanel = new JPanel();
        victoriesPanel.setLayout(new BoxLayout(victoriesPanel, BoxLayout.Y_AXIS));
        victoriesPanel.setBorder(new EmptyBorder(20, 10, 20, 20));
        victoriesPanel.add(trophiesLabel);
        victoriesPanel.add(highestTrophiesLabel);
        victoriesPanel.add(trioVictoriesLabel);
        victoriesPanel.add(duoVictoriesLabel);
        victoriesPanel.add(soloVictoriesLabel);

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.add(userAccountPanel);
        userPanel.add(victoriesPanel);

        JPanel matchScrollPanePanel = new JPanel();
        matchScrollPanePanel.setBorder(new EmptyBorder(0, 0, 0, 10));
        matchScrollPanePanel.setLayout(new BoxLayout(matchScrollPanePanel, BoxLayout.Y_AXIS));
        matchScrollPanePanel.setAlignmentX(CENTER_ALIGNMENT);
        matchScrollPanePanel.add(new JLabel("Matches: "));
        matchScrollPanePanel.add(matchScrollPane);

        JPanel brawlerScrollPanePanel = new JPanel();
        brawlerScrollPanePanel.setBorder(new EmptyBorder(0,10,0,0));
        brawlerScrollPanePanel.setLayout(new BoxLayout(brawlerScrollPanePanel, BoxLayout.Y_AXIS));
        brawlerScrollPanePanel.setAlignmentX(CENTER_ALIGNMENT);
        brawlerScrollPanePanel.add(new JLabel("Brawlers: "));
        brawlerScrollPanePanel.add(brawlerScrollPane);

        JPanel matchBrawlerPanel = new JPanel();
        matchBrawlerPanel.setLayout(new BoxLayout(matchBrawlerPanel, BoxLayout.X_AXIS));
        matchBrawlerPanel.setBorder(new EmptyBorder(20,20,20,20));
        matchBrawlerPanel.add(brawlerScrollPanePanel);
        matchBrawlerPanel.add(matchScrollPanePanel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setState("search");
                viewManagerModel.firePropertyChanged();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userPanel);
        this.add(matchBrawlerPanel);
        this.add(backButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final UserLookupState state = (UserLookupState) evt.getNewValue();

        tagLabel.setText("Tag: " + state.getTag());
        userNameLabel.setText("Username: " + state.getUsername());
        trophiesLabel.setText("Trophies: " + String.valueOf(state.getTrophies()));
        highestTrophiesLabel.setText("Highest Trophies: " + String.valueOf(state.getHighestTrophies()));
        trioVictoriesLabel.setText("Trio Victories: " + String.valueOf(state.getTrioVictories()));
        duoVictoriesLabel.setText("Duo Victories: " + String.valueOf(state.getDuoVictories()));
        soloVictoriesLabel.setText("Solo Victories: " + String.valueOf(state.getSoloVictories()));

        //TODO finish implementation of brawlers and matches
        brawlerPanel.removeAll();
        for(int i = 0; i < state.getBrawlers().length; i++) {
            brawlerPanel.add(new JLabel("TEMP_BRAWLER"));
        }

        matchPanel.removeAll();
        for(int i = 0; i < state.getMatches().length; i++) {
            matchPanel.add(new JLabel(state.getMatches()[i].getMap()));
        }

    }

    public String getViewName() {
        return viewName;
    }

}
