package use_case.user_lookup;

import entity.User;

public class UserLookupInteractor implements UserLookupInputBoundary {

	private final UserLookupDataAccessInterface userLookupDataAccessInterface;
	private final UserLookupOutputBoundary userLookupOutputBoundary;

	public UserLookupInteractor(UserLookupDataAccessInterface userLookupDataAccessInterface,
			UserLookupOutputBoundary userLookupOutputBoundary) {
		this.userLookupDataAccessInterface = userLookupDataAccessInterface;
		this.userLookupOutputBoundary = userLookupOutputBoundary;
	}

	@Override
	public void execute(UserLookupInputData userLookupInputData) {

		try {
			User user = userLookupDataAccessInterface.getUser(userLookupInputData.getTag());
			final UserLookupOutputData userLookupOutputData = new UserLookupOutputData(user);
			userLookupOutputBoundary.prepareSuccessView(userLookupOutputData);
		} catch (RuntimeException e) {
			userLookupOutputBoundary.prepareFailView(e.getMessage());
			return;
		}
	}
}
