package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.club_lookup.ClubLookupViewModel;
import view.ClubView;

public class ClubLookupUseCaseFactory {

        private ClubLookupUseCaseFactory() {
        }

        public static ClubView create(ViewManagerModel viewManagerModel, ClubLookupViewModel clubLookupViewModel) {
            return new ClubView(clubLookupViewModel, viewManagerModel);
        }
}
