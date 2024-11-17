package view;

import interface_adapter.brawler_lookup.BrawlerLookupController;
import interface_adapter.leaderboard_lookup.LeaderboardLookupController;
import interface_adapter.match_lookup.MatchLookupController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.user_lookup.UserLookupController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchView extends JPanel implements PropertyChangeListener {

    private final String viewName = "search";
    private final SearchViewModel searchViewModel;

    private final JTextField searchField = new JTextField();
    private final JLabel searchErrorField = new JLabel();

    private final JButton searchBrawlerButton;
    private final JButton searchPlayerButton;
    private final JButton searchMatchButton;
    private final JButton searchLeaderboardButton;

    public SearchView(SearchViewModel viewModel, BrawlerLookupController brawlerLookupController,
            UserLookupController userLookupController, MatchLookupController matchLookupController,
            LeaderboardLookupController leaderboardLookupController) {
        this.searchViewModel = viewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Player Tag:");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        searchField.setPreferredSize(new Dimension(200, searchField.getPreferredSize().height));
        final LabelTextPanel searchQuery = new LabelTextPanel(new JLabel("Search"), searchField);

        final JPanel searchByTagPanel = new JPanel();
        searchBrawlerButton = new JButton("Search Brawler");
        searchPlayerButton = new JButton("Search Player");
        searchMatchButton = new JButton("Search Match");

        searchByTagPanel.add(searchField);
        searchByTagPanel.add(searchBrawlerButton);
        searchByTagPanel.add(searchPlayerButton);
        searchByTagPanel.add(searchMatchButton);

        final JPanel leaderboardSearchPanel = new JPanel();
        final JLabel instructions = new JLabel("Search for top brawlers in leaderboard: Top");
        final Integer[] sizeChoices = new Integer[] { 5, 10, 15, 20 };
        final JComboBox<Integer> leaderboardSize = new JComboBox<Integer>(sizeChoices);
        viewModel.getState().setLeaderboardSize(sizeChoices[0]);
        searchLeaderboardButton = new JButton("Search Leaderboard");
        leaderboardSearchPanel.add(instructions);
        leaderboardSearchPanel.add(leaderboardSize);
        leaderboardSearchPanel.add(searchLeaderboardButton);

        searchBrawlerButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(searchBrawlerButton)) {
                            final SearchState currentState = searchViewModel.getState();

                            brawlerLookupController.execute(currentState.getQuery());
                        }
                    }
                });

        searchPlayerButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(searchPlayerButton)) {
                            final SearchState currentUserLookupState = searchViewModel.getState();

                            userLookupController.execute(currentUserLookupState.getQuery());
                        }
                    }
                });

        searchMatchButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(searchMatchButton)) {
                            final SearchState currentMatchLookupState = searchViewModel.getState();

                            matchLookupController.execute(currentMatchLookupState.getQuery());
                        }
                    }
                });

        searchField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SearchState currentState = searchViewModel.getState();
                currentState.setQuery(searchField.getText());
                searchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        leaderboardSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final SearchState currentState = searchViewModel.getState();
                final int size = (int) leaderboardSize.getSelectedItem();
                currentState.setLeaderboardSize(size);
                searchViewModel.setState(currentState);
            }
        });

        searchLeaderboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(searchLeaderboardButton)) {
                    final SearchState currentLeaderBoardState = searchViewModel.getState();
                    leaderboardLookupController.execute(currentLeaderBoardState.getLeaderboardSize());
                }
            }
        });

        this.add(title);
        this.add(searchErrorField);
        this.add(searchByTagPanel);
        this.add(leaderboardSearchPanel);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        final SearchState state = (SearchState) e.getNewValue();
        setFields(state);
        searchErrorField.setText(state.getSearchError());
    }

    private void setFields(SearchState state) {
        searchField.setText(state.getQuery());
    }

    public String getViewName() {
        return viewName;
    }
}
