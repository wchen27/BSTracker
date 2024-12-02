package view;

import interface_adapter.brawler_lookup.BrawlerLookupController;
import interface_adapter.club_lookup.ClubLookupController;
import interface_adapter.leaderboard_lookup.LeaderboardLookupController;
import interface_adapter.match_lookup.MatchLookupController;
import interface_adapter.previous_search.PreviousSearchController;
import interface_adapter.previous_search.PreviousSearchState;
import interface_adapter.previous_search.PreviousSearchViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.user_lookup.UserLookupController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchView extends JPanel implements PropertyChangeListener {

    private final String viewName = "search";
    private final SearchViewModel searchViewModel;
    private final PreviousSearchViewModel previousSearchViewModel;

    private final JTextField searchField = new JTextField();
    private final JLabel searchErrorField = new JLabel();

    private final JButton searchBrawlerButton;
    private final JButton searchPlayerButton;
    private final JButton searchMatchButton;
    private final JButton searchClubButton;
    private final JButton searchLeaderboardButton;
    
    private final JScrollPane previousSearchScrollPane;
    private final JPanel previousSearchPanel;
    private JLabel[] previousSearchLabels;

    public SearchView(SearchViewModel viewModel, PreviousSearchViewModel previousSearchViewModel,
     BrawlerLookupController brawlerLookupController,
            UserLookupController userLookupController, MatchLookupController matchLookupController,
            LeaderboardLookupController leaderboardLookupController, ClubLookupController clubLookupController,
            PreviousSearchController previousSearchController) {
        this.searchViewModel = viewModel;
        this.searchViewModel.addPropertyChangeListener(this);
        this.previousSearchViewModel = previousSearchViewModel;
        this.previousSearchViewModel.addPropertyChangeListener(this);

        final BufferedImage logo;
        try {
            logo = ImageIO.read(new File("src/resources/logo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final JLabel logoLabel = new JLabel(new ImageIcon(logo.getScaledInstance(100,
                100, Image.SCALE_SMOOTH)));
        logoLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(logoLabel);

        final JLabel title = new JLabel("Search:");
        title.setBorder(new EmptyBorder(0,20,0,10));

        searchField.setPreferredSize(new Dimension(200, searchField.getPreferredSize().height));
        final LabelTextPanel searchQuery = new LabelTextPanel(new JLabel("Search"), searchField);

        final JPanel searchByTagPanel = new JPanel();
        searchBrawlerButton = new JButton("Search Brawler");
        searchPlayerButton = new JButton("Search Player");
        searchMatchButton = new JButton("Search Match");
        searchClubButton = new JButton("Search Club");

        searchByTagPanel.add(searchBrawlerButton);
        searchByTagPanel.add(searchPlayerButton);
        searchByTagPanel.add(searchMatchButton);
        searchByTagPanel.add(searchClubButton);

        final JPanel leaderboardSearchPanel = new JPanel();
        leaderboardSearchPanel.setBorder(new EmptyBorder(10,0,0,0));
        final JLabel instructions = new JLabel("Search for top brawlers in leaderboard: Top");
        final Integer[] sizeChoices = new Integer[] { 5, 10, 15, 20 };
        final JComboBox<Integer> leaderboardSize = new JComboBox<Integer>(sizeChoices);
        viewModel.getState().setLeaderboardSize(sizeChoices[0]);
        leaderboardSize.setPreferredSize(new Dimension(100, 25));
        searchLeaderboardButton = new JButton("Search Leaderboard");
        leaderboardSearchPanel.add(instructions);
        leaderboardSearchPanel.add(leaderboardSize);
        leaderboardSearchPanel.add(searchLeaderboardButton);
        leaderboardSize.setSize(new Dimension(200,25));
        leaderboardSearchPanel.setMaximumSize(new Dimension(800,50));
        leaderboardSize.setBorder(new EmptyBorder(0,5,0,5));
        leaderboardSearchPanel.setBackground(Color.WHITE);

        previousSearchPanel = new JPanel();
        previousSearchPanel.setLayout(new BoxLayout(previousSearchPanel, BoxLayout.Y_AXIS));
        previousSearchPanel.setMaximumSize(new Dimension(600, 100));
        previousSearchScrollPane = new JScrollPane(previousSearchPanel);
        previousSearchScrollPane.setMaximumSize(new Dimension(600, 200));
        previousSearchScrollPane.setPreferredSize(new Dimension(600, 200));
        previousSearchScrollPane.setBackground(new Color(255, 255, 255));

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

        searchClubButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(searchClubButton)) {
                            final SearchState currentClubLookupState = searchViewModel.getState();

                            clubLookupController.execute(currentClubLookupState.getQuery());
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

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.add(title);
        searchPanel.add(searchField);
        searchPanel.setMaximumSize(new Dimension(200, 100));
        searchPanel.setBorder(new EmptyBorder(0,0,10,0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(searchPanel);
        buttonPanel.add(searchByTagPanel);
        buttonPanel.setMaximumSize(new Dimension(800, 200));

        JPanel buttonLeaderPanel = new JPanel();
        buttonLeaderPanel.add(buttonPanel);
        buttonLeaderPanel.add(leaderboardSearchPanel);
        buttonLeaderPanel.setMaximumSize(new Dimension(800,100));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel currentSearchPanel = new JPanel();
        currentSearchPanel.add(buttonPanel);
        currentSearchPanel.add(buttonLeaderPanel);
        currentSearchPanel.setMaximumSize(new Dimension(800,150));

        this.add(currentSearchPanel);
        this.add(previousSearchScrollPane);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.setBackground(new Color(255, 255, 255));
        currentSearchPanel.setBackground(new Color(255, 255, 255));
        searchPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setBackground(new Color(255, 255, 255));
        searchByTagPanel.setBackground(new Color(255, 255, 255));
        buttonLeaderPanel.setBackground(new Color(255, 255, 255));

        previousSearchController.execute();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if(e.getNewValue() instanceof SearchState) {
            final SearchState state = (SearchState) e.getNewValue();
            setFields(state);
            searchErrorField.setText(state.getSearchError());
        } else if (e.getNewValue() instanceof PreviousSearchState) {

            final PreviousSearchState state = (PreviousSearchState) e.getNewValue();
            previousSearchPanel.removeAll();
            previousSearchLabels = new JLabel[state.getPreviousSearches().length];
            for(int i = 0; i < state.getPreviousSearches().length; i++) {
                previousSearchLabels[i] = new JLabel(state.getPreviousSearches()[i]);
                previousSearchPanel.add(previousSearchLabels[i]);
            }
            previousSearchScrollPane.repaint();
            previousSearchScrollPane.revalidate();
        }
    }

    private void setFields(SearchState state) {
        searchField.setText(state.getQuery());
    }

    public String getViewName() {
        return viewName;
    }
}
