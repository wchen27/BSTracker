package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.match_lookup.MatchLookupState;
import interface_adapter.match_lookup.MatchLookupViewModel;

import java.util.List;
import entity.Match;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class MatchView extends JPanel implements PropertyChangeListener{

    private final String viewName = "match lookup";
    private final MatchLookupViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    private final JLabel title;
    private final JButton backButton;

    public MatchView(MatchLookupViewModel viewModel, ViewManagerModel viewManagerModel) {
        super();
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        viewModel.addPropertyChangeListener(this);

        // Create the big title label
        title = new JLabel("Match Lookup\n");
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Create the Back button
        backButton = new JButton("Back");

        // Add functionality to the Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setState("search");
                viewManagerModel.firePropertyChanged();
            }
        });

        // Set the layout for the view
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(backButton);

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MatchLookupState state = (MatchLookupState) evt.getNewValue();

        // Populate the match data into individual JPanels
        List<Match> matches = state.getMatches();
        for (Match match : matches) {
            JPanel matchPanel = new JPanel();
            // Set the layout of each Match panel
            matchPanel.setLayout(new BoxLayout(matchPanel, BoxLayout.X_AXIS));

            // Set the victory/defeat text and color
            JLabel result = new JLabel (match.isVictory() ? "Victory" : "Defeat ");
            result.setForeground(match.isVictory() ? Color.BLUE : Color.RED);

            // Format the time of the match
            String time = match.getTime();
            String fmtTime = time.substring(4, 6) + "/" + time.substring(6, 8) + "/" + time.substring(0, 4) + " " + time.substring(9, 11) + ":" + time.substring(11, 13);
            JLabel matchTime = new JLabel(fmtTime);

            // Add the mode of the match
            JLabel mode = new JLabel(match.getMode());

            // Add the map the match was played on
            JLabel map = new JLabel(match.getMap());

            // Get the trophy change result of the map and calculate the offset so the text lines up
            int trophyChange = match.getTrophyChange();
            String trophyChangeString = trophyChange >= 0 ? "+" + trophyChange : "" + trophyChange;
            trophyChangeString = trophyChangeString.length() < 3 ? " " + trophyChangeString : trophyChangeString;
            JLabel trophyChangeLabel = new JLabel(trophyChangeString);

            // Get the star player of the match
            JLabel starPlayer = new JLabel("Star Player: " + match.getStarPlayer());

            // Format everything together nicely
            matchPanel.add(Box.createHorizontalStrut(50));
            matchPanel.add(result);
            matchPanel.add(Box.createHorizontalStrut(10));
            matchPanel.add(trophyChangeLabel);
            matchPanel.add(Box.createHorizontalStrut(10));
            matchPanel.add(matchTime);
            matchPanel.add(Box.createHorizontalStrut(10));
            matchPanel.add(mode);
            matchPanel.add(Box.createHorizontalStrut(10));
            matchPanel.add(map);
            matchPanel.add(Box.createHorizontalStrut(10));
            matchPanel.add(starPlayer);
            matchPanel.add(Box.createVerticalStrut(10));
            // Add the match panel to the view
            this.add(matchPanel);
        }
        

    }

    public String getViewName() {
        return viewName;
    }
}
