package il.ac.hit.CashFlowManagement.view;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * this interface define the operations that each Form/Frame must implement
 */
public interface IView {

    /**
     * Show dialog of the form
     */
    void showForm();

    /**
     * Close the dialog of the form
     * @param form the form who should be closed, can't be null
     */
    default void closeForm(@NotNull Window form){
        form.setVisible(false);
        form.dispose();
    }

    /**
     * set the form view model to the ManagementViewModel instance
     */
    void setViewModel();
}
