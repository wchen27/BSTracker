package app;

import interface_adapter.user_lookup.UserLookupController;
import view.SearchView;
import interface_adapter.brawler_lookup.BrawlerLookupController;
import interface_adapter.search.SearchViewModel;

public final class SearchUseCaseFactory {
    private SearchUseCaseFactory() {
    }

    public static SearchView create(
        SearchViewModel searchViewModel, 
        BrawlerLookupController brawlerLookupController,
        UserLookupController userLookupController) {

            return new SearchView(searchViewModel, brawlerLookupController, userLookupController);
    }

    
    

}
