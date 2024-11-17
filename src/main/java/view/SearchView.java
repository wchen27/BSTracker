package view;

import interface_adapter.brawler_lookup.BrawlerLookupController;
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

    private BrawlerLookupController brawlerLookupController;
    private UserLookupController userLookupController;
    private MatchLookupController matchLookupController;

    public SearchView(SearchViewModel viewModel, BrawlerLookupController brawlerLookupController,
            UserLookupController userLookupController, MatchLookupController matchLookupController) {
        this.searchViewModel = viewModel;
        this.searchViewModel.addPropertyChangeListener(this);
        this.brawlerLookupController = brawlerLookupController;
        this.userLookupController = userLookupController;
        this.matchLookupController = matchLookupController;

        final JLabel title = new JLabel("Player Tag:");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        searchField.setPreferredSize(new Dimension(200, searchField.getPreferredSize().height));
        final LabelTextPanel searchQuery = new LabelTextPanel(new JLabel("Search"), searchField);

        final JPanel buttons = new JPanel();
        searchBrawlerButton = new JButton("Search Brawler");
        searchPlayerButton = new JButton("Search Player");
        searchMatchButton = new JButton("Search Match");

        buttons.add(searchBrawlerButton);
        buttons.add(searchPlayerButton);
        buttons.add(searchMatchButton);

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
                }
        );

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

        this.add(title);
        this.add(searchField);
        this.add(searchErrorField);
        this.add(buttons);
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
