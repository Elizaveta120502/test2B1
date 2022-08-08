package com.test.task.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Entity implements Serializable {
    int id;
    Double accountNumber;
    Double openingBalanceAssets;
    Double openingBalanceLiabiblity;
    Double circulationDebit;
    Double circulationCredit;
    Double closingBalanceAssets;
    Double closingBalanceLiability;
    String nameOfClass;
    @Serial
    private static final long serialVersionUID = -2267363635841127684L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountNumber(Double accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOpeningBalanceAssets(Double openingBalanceAssets) {
        this.openingBalanceAssets = openingBalanceAssets;
    }

    public void setOpeningBalanceLiabiblity(Double openingBalanceLiabiblity) {
        this.openingBalanceLiabiblity = openingBalanceLiabiblity;
    }

    public void setCirculationDebit(Double circulationDebit) {
        this.circulationDebit = circulationDebit;
    }

    public void setCirculationCredit(Double circulationCredit) {
        this.circulationCredit = circulationCredit;
    }

    public void setClosingBalanceAssets(Double closingBalanceAssets) {
        this.closingBalanceAssets = closingBalanceAssets;
    }

    public void setClosingBalanceLiability(Double closingBalanceLiability) {
        this.closingBalanceLiability = closingBalanceLiability;
    }

    public void setNameOfClass(String nameOfClass) {
        this.nameOfClass = nameOfClass;
    }

    public Double getAccountNumber() {
        return accountNumber;
    }

    public Double getOpeningBalanceAssets() {
        return openingBalanceAssets;
    }

    public Double getOpeningBalanceLiabiblity() {
        return openingBalanceLiabiblity;
    }

    public Double getCirculationDebit() {
        return circulationDebit;
    }

    public Double getCirculationCredit() {
        return circulationCredit;
    }

    public Double getClosingBalanceAssets() {
        return closingBalanceAssets;
    }

    public Double getClosingBalanceLiability() {
        return closingBalanceLiability;
    }

    public String getNameOfClass() {
        return nameOfClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity entity = (Entity) o;
        return getId() == entity.getId() && Objects.equals(getAccountNumber(), entity.getAccountNumber()) && Objects.equals(getOpeningBalanceAssets(), entity.getOpeningBalanceAssets()) && Objects.equals(getOpeningBalanceLiabiblity(), entity.getOpeningBalanceLiabiblity()) && Objects.equals(getCirculationDebit(), entity.getCirculationDebit()) && Objects.equals(getCirculationCredit(), entity.getCirculationCredit()) && Objects.equals(getClosingBalanceAssets(), entity.getClosingBalanceAssets()) && Objects.equals(getClosingBalanceLiability(), entity.getClosingBalanceLiability()) && Objects.equals(getNameOfClass(), entity.getNameOfClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountNumber(), getOpeningBalanceAssets(), getOpeningBalanceLiabiblity(), getCirculationDebit(), getCirculationCredit(), getClosingBalanceAssets(), getClosingBalanceLiability(), getNameOfClass());
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", openingBalanceAssets=" + openingBalanceAssets +
                ", openingBalanceLiabiblity=" + openingBalanceLiabiblity +
                ", circulationDebit=" + circulationDebit +
                ", circulationCredit=" + circulationCredit +
                ", closingBalanceAssets=" + closingBalanceAssets +
                ", closingBalanceLiability=" + closingBalanceLiability +
                ", nameOfClass='" + nameOfClass + '\'' +
                '}';
    }
}
