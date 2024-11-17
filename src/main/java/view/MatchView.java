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

        title = new JLabel("Match Lookup\n");
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
        final MatchLookupState state = (MatchLookupState) evt.getNewValue();

        List<Match> matches = state.getMatches();
        for (Match match : matches) {
            /* 
             * Create a JPanel for each match and add it to the view
             * each JPanel should look like this:
             * Result    MatchTime (convert from YYYYMMDDTHHMMSSZ to MM/DD/YYYY)
             * Mode   Map  
             * Trophy Change:               Star Player: 
             */
            JPanel matchPanel = new JPanel();
            matchPanel.setLayout(new BoxLayout(matchPanel, BoxLayout.X_AXIS));
            JLabel result = new JLabel (match.isVictory() ? "Victory" : "Defeat ");
            result.setForeground(match.isVictory() ? Color.BLUE : Color.RED);
            String time = match.getTime();
            String fmtTime = time.substring(4, 6) + "/" + time.substring(6, 8) + "/" + time.substring(0, 4) + " " + time.substring(9, 11) + ":" + time.substring(11, 13);
            JLabel matchTime = new JLabel(fmtTime);
            JLabel mode = new JLabel(match.getMode());
            JLabel map = new JLabel(match.getMap());
            int trophyChange = match.getTrophyChange();
            JLabel trophyChangeLabel = new JLabel(trophyChange > 0 ? "+" + trophyChange : "" + trophyChange);
            JLabel starPlayer = new JLabel("Star Player: " + match.getStarPlayer());
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
            this.add(matchPanel);
        }
        

    }

    public String getViewName() {
        return viewName;
    }
}
