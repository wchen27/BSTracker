package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entity.Match;
import interface_adapter.ViewManagerModel;
import interface_adapter.user_lookup.UserLookupState;
import interface_adapter.user_lookup.UserLookupViewModel;

/*
 * The view when the user looks up a user
 */
public class UserView extends JPanel implements PropertyChangeListener {

    private final String viewName = "user lookup";
    private final UserLookupViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    private final JLabel title;
    private final JLabel tagLabel;
    private final JLabel userNameLabel;
    private final JLabel trophiesLabel;
    private final JLabel highestTrophiesLabel;
    private final JLabel trioVictoriesLabel;
    private final JLabel duoVictoriesLabel;
    private final JLabel soloVictoriesLabel;
    private final JLabel performanceLabel;
    private final JPanel brawlerPanel;
    private final JPanel matchPanel;
    private final JScrollPane brawlerScrollPane;
    private final JScrollPane matchScrollPane;
    private final JButton backButton;
    JPanel matchScrollPanePanel = new JPanel();

    public UserView(UserLookupViewModel viewModel, ViewManagerModel viewManagerModel) {
        super();
        // Sets up the Clean Architecture requirements
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        viewModel.addPropertyChangeListener(this);

        // Sets up the java swing components
        title = new JLabel("User Lookup");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(20f));
        title.setBackground(Color.WHITE);

        tagLabel = new JLabel("");

        userNameLabel = new JLabel("");

        trophiesLabel = new JLabel("");

        highestTrophiesLabel = new JLabel("");

        trioVictoriesLabel = new JLabel("");

        duoVictoriesLabel = new JLabel("");

        soloVictoriesLabel = new JLabel("");
        performanceLabel = new JLabel("");
        
        brawlerPanel = new JPanel();
        brawlerPanel.setLayout(new BoxLayout(brawlerPanel, BoxLayout.X_AXIS));
        brawlerPanel.setBackground(Color.WHITE);
        matchPanel = new JPanel();
        matchPanel.setLayout(new BoxLayout(matchPanel, BoxLayout.Y_AXIS));
        matchPanel.setBackground(Color.WHITE);

        //TODO Make this a bit better and editiable
        brawlerScrollPane = new JScrollPane();
        
        matchScrollPane = new JScrollPane();
        

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set up panels for a nicer overall look
        JPanel userAccountPanel = new JPanel();
        userAccountPanel.setLayout(new BoxLayout(userAccountPanel, BoxLayout.Y_AXIS));
        userAccountPanel.setBorder(new EmptyBorder(20,20,20,10));
        userAccountPanel.add(userNameLabel);
        userAccountPanel.add(tagLabel);
        userAccountPanel.setBackground(Color.WHITE);
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
        victoriesPanel.add(performanceLabel);
        victoriesPanel.setBackground(Color.WHITE);

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.add(userAccountPanel);
        userPanel.add(victoriesPanel);
        userPanel.setBackground(Color.WHITE);

        matchScrollPanePanel.setBorder(new EmptyBorder(0, 0, 0, 10));
        matchScrollPanePanel.setLayout(new BoxLayout(matchScrollPanePanel, BoxLayout.Y_AXIS));
        matchScrollPanePanel.setAlignmentX(CENTER_ALIGNMENT);
        matchScrollPanePanel.add(new JLabel("Matches: "));
        matchScrollPanePanel.add(matchPanel);
        matchScrollPanePanel.setBackground(Color.WHITE);

        brawlerScrollPane.add(brawlerPanel);
        JPanel brawlerScrollPanePanel = new JPanel();
        brawlerScrollPanePanel.setBorder(new EmptyBorder(0,10,0,0));
        brawlerScrollPanePanel.setLayout(new BoxLayout(brawlerScrollPanePanel, BoxLayout.Y_AXIS));
        brawlerScrollPanePanel.setAlignmentX(CENTER_ALIGNMENT);
        brawlerScrollPanePanel.add(new JLabel("Brawlers: "));
        brawlerScrollPanePanel.add(brawlerScrollPane);
        brawlerScrollPanePanel.setBackground(Color.WHITE);

        JPanel matchBrawlerPanel = new JPanel();
        matchBrawlerPanel.setLayout(new BoxLayout(matchBrawlerPanel, BoxLayout.X_AXIS));
        matchBrawlerPanel.setBorder(new EmptyBorder(20,20,20,20));
        matchBrawlerPanel.add(brawlerScrollPanePanel);
        matchBrawlerPanel.add(matchScrollPanePanel);
        matchBrawlerPanel.setBackground(Color.WHITE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setState("search");
                viewManagerModel.firePropertyChanged();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adds the required components to the panel
        this.add(title);
        this.add(userPanel);
        this.add(matchBrawlerPanel);
        this.add(backButton);
        this.setBackground(Color.WHITE);
    }

    /**
     * Updates the view when a change is made
     * 
     * @param evt the event when a change is made
     */
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
        performanceLabel.setText("Performance: " + String.valueOf(state.getPerformance()));


        //TODO finish implementation of brawlers and matches
        brawlerPanel.removeAll();
        for(int i = 0; i < state.getBrawlers().length; i++) {
            brawlerPanel.add(new JLabel("TEMP_BRAWLER"));
        }

        matchPanel.removeAll();
        for (Match match : state.getMatches().subList(0, 10)) {
            JPanel currMatchPanel = new JPanel();
            currMatchPanel.setLayout(new BoxLayout(currMatchPanel, BoxLayout.X_AXIS));
            JScrollPane currMatchScrollPane = new JScrollPane(currMatchPanel);
            JLabel result = new JLabel (match.isVictory() ? "Victory" : "Defeat ");
            result.setForeground(match.isVictory() ? Color.BLUE : Color.RED);

            JLabel mode = new JLabel(match.getMode());

            JLabel map = new JLabel(match.getMap());

            int trophyChange = match.getTrophyChange();
            String trophyChangeString = trophyChange >= 0 ? "+" + trophyChange : "" + trophyChange;
            trophyChangeString = trophyChangeString.length() < 3 ? " " + trophyChangeString : trophyChangeString;
            JLabel trophyChangeLabel = new JLabel(trophyChangeString);

            currMatchPanel.add(Box.createHorizontalStrut(50));
            currMatchPanel.add(result);
            currMatchPanel.add(Box.createHorizontalStrut(10));
            currMatchPanel.add(trophyChangeLabel);
            currMatchPanel.add(Box.createHorizontalStrut(10));
            currMatchPanel.add(mode);
            currMatchPanel.add(Box.createHorizontalStrut(10));
            currMatchPanel.add(map);
            currMatchPanel.add(Box.createHorizontalStrut(10));
            currMatchScrollPane.add(currMatchPanel);
            currMatchPanel.setPreferredSize(new Dimension(350, 25));
            currMatchPanel.setBackground(Color.WHITE);
            matchPanel.add(currMatchPanel);
        }

    }

    
    public String getViewName() {
        return viewName;
    }

}
