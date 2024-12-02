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
    private final JLabel winrateLabel;
    private final JLabel trophyChange;
    private final JButton backButton;
    private JScrollPane matchesScrollPane;
    private JPanel matchesPanel;

    public MatchView(MatchLookupViewModel viewModel, ViewManagerModel viewManagerModel) {
        super();
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        viewModel.addPropertyChangeListener(this);

        title = new JLabel("Match Lookup\n");
        title.setAlignmentX(CENTER_ALIGNMENT);

        backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);

        winrateLabel = new JLabel("Winrate");
        winrateLabel.setAlignmentX(CENTER_ALIGNMENT);

        trophyChange = new JLabel("Trophies Gained");
        trophyChange.setAlignmentX(CENTER_ALIGNMENT);

        matchesScrollPane = new JScrollPane();
        matchesPanel = new JPanel();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setState("search");
                viewManagerModel.firePropertyChanged();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        matchesScrollPane = new JScrollPane(matchesPanel);

        this.add(title);
        this.add(backButton);
        this.add(winrateLabel);
        this.add(trophyChange);

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MatchLookupState state = (MatchLookupState) evt.getNewValue();

        matchesPanel.removeAll();
        matchesPanel.setLayout(new BoxLayout(matchesPanel, BoxLayout.Y_AXIS));

        List<Match> matches = state.getMatches();
        double winrate = state.getWinrate();
        winrateLabel.setText("Winrate: " + winrate + "%");
        int trophyChanged = state.getTrophyChange();
        trophyChange.setText("Trophies Gained: " + trophyChanged);
        for (Match match : matches) {
            JPanel matchPanel = new JPanel();
            matchPanel.setLayout(new BoxLayout(matchPanel, BoxLayout.X_AXIS));
            JScrollPane matchScrollPane = new JScrollPane(matchPanel);
            JLabel result = new JLabel (match.isVictory() ? "Victory" : "Defeat ");
            result.setForeground(match.isVictory() ? Color.BLUE : Color.RED);

            String time = match.getTime();
            String fmtTime = time.substring(4, 6) + "/" + time.substring(6, 8) + "/" + time.substring(0, 4) + " " + time.substring(9, 11) + ":" + time.substring(11, 13);
            JLabel matchTime = new JLabel(fmtTime);

            JLabel mode = new JLabel(match.getMode());

            JLabel map = new JLabel(match.getMap());

            int trophyChange = match.getTrophyChange();
            String trophyChangeString = trophyChange >= 0 ? "+" + trophyChange : "" + trophyChange;
            trophyChangeString = trophyChangeString.length() < 3 ? " " + trophyChangeString : trophyChangeString;
            JLabel trophyChangeLabel = new JLabel(trophyChangeString);

            JLabel starPlayer = new JLabel("Star Player: " + match.getStarPlayer());


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
            matchesPanel.add(matchScrollPane);
        }

        this.add(matchesScrollPane);

        this.repaint();
        this.revalidate();

    }

    public String getViewName() {
        return viewName;
    }
}
