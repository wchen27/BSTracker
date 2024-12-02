package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard_lookup.LeaderboardLookupState;
import interface_adapter.leaderboard_lookup.LeaderboardLookupViewModel;

import java.awt.*;
import java.util.Collections;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

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
		title.setBackground(Color.WHITE);

		backButton = new JButton("Back");
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		backButton.setBackground(Color.WHITE);

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
		this.setBackground(Color.WHITE);

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
		tableHeader.setBackground(Color.WHITE);
		this.add(tableHeader);

		List<Map.Entry<String, Integer>> sortedBrawlerFrequency = new ArrayList<>(brawlerFrequency.entrySet());
		Collections.sort(sortedBrawlerFrequency, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

		for (Map.Entry<String, Integer> entry : sortedBrawlerFrequency) {
			JPanel row = new JPanel();
			JLabel brawlerName = new JLabel(entry.getKey());
			JLabel frequency = new JLabel(String.valueOf(entry.getValue()));
			row.add(brawlerName);
			row.add(frequency);
			row.setBackground(Color.WHITE);
			this.add(row);
		}
	}

	public String getViewName() {
		return viewName;
	}
}
