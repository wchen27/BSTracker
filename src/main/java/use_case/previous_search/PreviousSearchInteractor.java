package use_case.previous_search;

/*
 * The interactor for the previous search which gets the previous searches
 */
public class PreviousSearchInteractor implements PreviousSearchInputBoundary{

    final private PreviousSearchDataAccessInterface dataAccessInterface;
    final private PreviousSearchOutputBoundary outputBoundary;

    public PreviousSearchInteractor(PreviousSearchDataAccessInterface dataAccessInterface,
     PreviousSearchOutputBoundary outputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = outputBoundary;
    }

    /*
     * Gets the previous searches and then sends it to the presenter
     */
    @Override
    public void execute() {
        try {
            String[] previousSearches = dataAccessInterface.getPreviousSearches();
            final PreviousSearchOutputData outputData = new PreviousSearchOutputData(previousSearches);
            outputBoundary.prepareSuccessView(outputData);
        } catch(Exception ex) {
        }
    }
    
    @Override
    public void execute(String search) {
        try {
            dataAccessInterface.addSearch(search);
            String[] prev = dataAccessInterface.getPreviousSearches();
            final PreviousSearchOutputData outputData = new PreviousSearchOutputData(prev);
            outputBoundary.prepareSuccessView(outputData);
        } catch(Exception ex) {
        }
    }
}
