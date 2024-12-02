package interface_adapter;

/*
 * The View Manager Model which controlls which view is currently being seen by the user
 */
public class ViewManagerModel extends ViewModel<String> {
    

    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }
}
