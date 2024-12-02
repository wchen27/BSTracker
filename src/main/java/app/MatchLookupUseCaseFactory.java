package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.match_lookup.MatchLookupViewModel;
import view.MatchView;

public class MatchLookupUseCaseFactory {
    private MatchLookupUseCaseFactory() {}

    /**
     * Creates the ViewModels for the MatchView.
     * @param viewManagerModel The ViewManagerModel for the MatchView
     * @param matchLookupViewModel the matchLookupViewModel for the MatchView
     * @return A MatchView with the specified ViewModels.
     */
    public static MatchView create(ViewManagerModel viewManagerModel, MatchLookupViewModel matchLookupViewModel) {
        return new MatchView(matchLookupViewModel, viewManagerModel);
    }
}
