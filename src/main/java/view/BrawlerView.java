package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.brawler_lookup.BrawlerLookupState;
import interface_adapter.brawler_lookup.BrawlerLookupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BrawlerView extends JPanel implements PropertyChangeListener {

    private final String viewName = "brawler lookup";
    private final BrawlerLookupViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    private final JLabel title;
    private final JButton backButton;

    public BrawlerView(BrawlerLookupViewModel viewModel, ViewManagerModel viewManagerModel) {
        super();
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        viewModel.addPropertyChangeListener(this);

        // Title
        title = new JLabel("Brawler Lookup");
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Back Button
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
        final BrawlerLookupState state = (BrawlerLookupState) evt.getNewValue();

        // Clear previous content
        this.removeAll();

        // Add title and back button
        this.add(title);
        this.add(backButton);

        // Add Brawler Details
        JPanel brawlerPanel = new JPanel();
        brawlerPanel.setLayout(new BoxLayout(brawlerPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Name: " + state.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel roleLabel = new JLabel("Role: " + state.getRole());
        JLabel rarityLabel = new JLabel("Rarity: " + state.getRarity());

        JLabel statsLabel = new JLabel("Stats:");
        JTextArea statsArea = new JTextArea(state.getStats());
        statsArea.setEditable(false);

        JLabel abilitiesLabel = new JLabel("Abilities:");
        JTextArea abilitiesArea = new JTextArea(state.getAbilities());
        abilitiesArea.setEditable(false);

        brawlerPanel.add(nameLabel);
        brawlerPanel.add(roleLabel);
        brawlerPanel.add(rarityLabel);
        brawlerPanel.add(statsLabel);
        brawlerPanel.add(new JScrollPane(statsArea));
        brawlerPanel.add(abilitiesLabel);
        brawlerPanel.add(new JScrollPane(abilitiesArea));

        this.add(brawlerPanel);

        // Refresh UI
        this.revalidate();
        this.repaint();
    }

    public String getViewName() {
        return viewName;
    }
}
