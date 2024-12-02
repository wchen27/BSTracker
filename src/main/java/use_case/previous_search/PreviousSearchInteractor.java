package use_case.previous_search;

public class PreviousSearchInteractor implements PreviousSearchInputBoundary{

    final private PreviousSearchDataAccessInterface dataAccessInterface;
    final private PreviousSearchOutputBoundary outputBoundary;

    public PreviousSearchInteractor(PreviousSearchDataAccessInterface dataAccessInterface, PreviousSearchOutputBoundary outputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute() {
        try {
            String[] previousSearches = dataAccessInterface.getPreviousSearches();
            final PreviousSearchOutputData outputData = new PreviousSearchOutputData(previousSearches);
            outputBoundary.prepareSuccessView(outputData);
        } catch(Exception ex) {
            outputBoundary.prepareFailView(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
