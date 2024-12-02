package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard_lookup.LeaderboardLookupState;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;

import java.util.Collections;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

public class LeaderboardView extends JPanel implements PropertyChangeListener {

	private final String viewName = "leaderboard lookup";
	private final LeaderboardLookupViewModel viewModel;
	private final ViewManagerModel viewManagerModel;

	private final JLabel title;
	private final JButton backButton;

	public LeaderboardView(LeaderboardLookupViewModel viewModel, ViewManagerModel viewManagerModel) {
		super();
		this.viewModel = viewModel;
		this.viewManagerModel = viewManagerModel;
		viewModel.addPropertyChangeListener(this);

		title = new JLabel("Top Brawlers on Global Leaderboard");
		title.setAlignmentX(CENTER_ALIGNMENT);

		backButton = new JButton("Back");
		backButton.setAlignmentX(CENTER_ALIGNMENT);

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
		final LeaderboardLookupState state = (LeaderboardLookupState) evt.getNewValue();
		final String subtitle;
		JPanel tableHeader = new JPanel();
		Map<String, Integer> brawlerFrequency = state.getBrawlerFrequency();
		if (brawlerFrequency.isEmpty()) {
			subtitle = "No data found";
		} else {
			subtitle = "Brawler frequency from the top " + state.getNumUsers() + " users globally:";
		}
		tableHeader.add(new JLabel(subtitle));
		this.add(tableHeader);

		List<Map.Entry<String, Integer>> sortedBrawlerFrequency = new ArrayList<>(brawlerFrequency.entrySet());
		Collections.sort(sortedBrawlerFrequency, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

		// Create header names for the result table.
		String[] columnNames = { "Brawler Name", "Frequency Played" };

		// Construct a data object to be loaded into the result table.
		String[][] tabledata = new String[11][2];
		tabledata[0] = new String[] { "Brawler Name", "Frequency Played" };
		int count = 1;
		for (Map.Entry<String, Integer> entry : sortedBrawlerFrequency) {
			if (count >= 11) {
				break;
			}
			String brawlerName = entry.getKey();
			String frequency = String.valueOf(entry.getValue());
			tabledata[count] = new String[] { brawlerName, frequency };
			count++;
		}
		// Create a table with the column names and data and add it to the frame.
		// Also make edits to make the column width nicer.
		JTable table = new JTable(tabledata, columnNames);
		JPanel tablePanel = new JPanel();

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(120);
		columnModel.getColumn(1).setPreferredWidth(120);
		tablePanel.add(table);
		this.add(tablePanel);
	}

	public String getViewName() {
		return viewName;
	}
}
