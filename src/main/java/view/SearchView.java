package view;

import entity.Brawler;
import interface_adapter.brawler_lookup.BrawlerLookupController;
import interface_adapter.brawler_lookup.BrawlerLookupState;
import interface_adapter.brawler_lookup.BrawlerLookupViewModel;
import interface_adapter.user_lookup.UserLookupController;
import interface_adapter.user_lookup.UserLookupState;
import interface_adapter.user_lookup.UserLookupViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchView extends JPanel implements PropertyChangeListener{

    private final String viewName = "Search";
    private final BrawlerLookupViewModel brawlerViewModel;
    private final BrawlerLookupController brawlerController;

    private final UserLookupViewModel userViewModel;
    private final UserLookupController userController;

    private final JTextField searchField = new JTextField();
    private final JLabel searchErrorField = new JLabel();

    private final JButton searchBrawlerButton;
    private final JButton searchPlayerButton;


    public SearchView(BrawlerLookupViewModel brawlerLookupViewModel, BrawlerLookupController controller) {
        this.BrawlerController = controller;
        this.BrawlerViewModel = brawlerLookupViewModel;
        this.BrawlerViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Search");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        final LabelTextPanel searchQuery = new LabelTextPanel(new JLabel("Search"), searchField);

        final JPanel buttons = new JPanel();
        searchBrawlerButton = new JButton("Search Brawler");
        searchPlayerButton = new JButton("Search Player");
        buttons.add(searchBrawlerButton);
        buttons.add(searchPlayerButton);

        searchBrawlerButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if (e.getSource().equals(searchBrawlerButton)){
                            final BrawlerLookupState currentBrawlerLookupState = BrawlerLookupViewModel.getState();

                            BrawlerLookupController.execute(currentBrawlerLookupState.getSearchQuery());
                        }
                    }
                }
        );

        searchPlayerButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if (e.getSource().equals(searchPlayerButton)){
                            final UserLookupState currentUserLookupState = UserLookupViewModel.getState();

                            UserLookupController.execute(currentUserLookupState.getSearchQuery());
                        }
                    }
                }
        );

        searchField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final BrawlerLookupState currentBrawlerLookupState = BrawlerLookupViewModel.getState();
                final UserLookupState currentUserLookupState = UserLookupViewModel.getState();
                currentBrawlerLookupState.setBrawler(searchField.getText());
                currentUserLookupState.setUser(searchField.getText());
                brawlerViewModel.setState(currentBrawlerLookupState);
                userViewModel.setState(currentUserLookupState);
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
        this.add(searchQuery);
        this.add(searchErrorField);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        final BrawlerLookupState state = (BrawlerLookupState) e.getNewValue();
        setFields(state);
        searchErrorField.setText(state.getSearchError());
    }

    private void setFields(BrawlerLookupState state) {
        searchField.setText(state.getSearchQuery());
    }

    public String getViewName() {
        return viewName;
    }
}
