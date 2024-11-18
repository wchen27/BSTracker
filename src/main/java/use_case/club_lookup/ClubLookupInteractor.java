package use_case.club_lookup;

import entity.User;

import java.util.List;

public class ClubLookupInteractor implements ClubLookupInputBoundary {

    private final ClubLookupDataAccessInterface clubLookupDataAccessInterface;
    private final ClubLookupOutputBoundary clubLookupOutputBoundary;

    public ClubLookupInteractor(ClubLookupDataAccessInterface clubLookupDataAccessInterface, ClubLookupOutputBoundary clubLookupOutputBoundary) {
        this.clubLookupDataAccessInterface = clubLookupDataAccessInterface;
        this.clubLookupOutputBoundary = clubLookupOutputBoundary;
    }

    public void execute(ClubLookupInputData clubLookupInputData) {
        try {
            List<User> members = clubLookupDataAccessInterface.getMembers(clubLookupInputData.getTag());
            final ClubLookupOutputData clubLookupOutputData = new ClubLookupOutputData(members);
            clubLookupOutputBoundary.prepareSuccessView(clubLookupOutputData);
        } catch (RuntimeException e) {
            clubLookupOutputBoundary.prepareFailView(e.getMessage());
        }
    }

}
