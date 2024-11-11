package interface_adapter.search;

import interface_adapter.ViewModel;

public class SearchViewModel extends ViewModel<SearchState> {

	public SearchViewModel() {
		super("search");
		setState(new SearchState());
	}
}
