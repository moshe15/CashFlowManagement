package il.ac.hit.CashFlowManagement;

import il.ac.hit.CashFlowManagement.viewmodel.IViewModel;
import il.ac.hit.CashFlowManagement.viewmodel.ManagementViewModel;

/**
 * The program class
 * @author Moshiko Davila 308459841
 * @author Sagi Sela 201325560
 * @version March 24 , 2020.
 */
public class Program {
    public static void main(String[] args)
    {
        IViewModel viewModel = ManagementViewModel.getInstance();

        viewModel.startProgram();
    }
}