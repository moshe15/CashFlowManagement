package il.ac.hit.CashFlowManagement.model;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  The Expense class
 */
public class Expense
{
    private Date date;
    private String classification, details;
    private double cost;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Expense C'tor
     * @param iDateStr Date of new expense, can't be null
     * @param iClassification Classification of new expense, can't be null
     * @param iDetails New expense details, can't be null
     * @param iCost New expense cost, can't be null
     */
    public Expense(@NotNull String iDateStr, @NotNull String iClassification, @NotNull String iDetails, @NotNull double iCost) {
        try{
            setDate(simpleDateFormat.parse(iDateStr));
            setClassification(iClassification);
            setDetails(iDetails);
            setCost(iCost);
        } catch(ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String i_Date) {
        try {
            setDate(simpleDateFormat.parse(i_Date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
