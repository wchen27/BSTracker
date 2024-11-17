package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.match_lookup.MatchLookupViewModel;
import view.MatchView;

public class MatchLookupUseCaseFactory {
    private MatchLookupUseCaseFactory() {}

    public static MatchView create(ViewManagerModel viewManagerModel, MatchLookupViewModel matchLookupViewModel) {
        return new MatchView(matchLookupViewModel, viewManagerModel);
    }
}
