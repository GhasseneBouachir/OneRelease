package org.onerelease.model;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.onerelease.enumeration.*;
import static org.openxava.jpa.XPersistence.getManager;

@Embeddable
public class EstimationLine {

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
//    @OnChange(OnChangeFunctionalityAction.class)
    private Functionality functionality;

    private EstimationLineType estimationLineType;

    @Required
    private BigDecimal quantity;

    @Required
    private int complexFactoryCharge;

    public EstimationLineType getEstimationLineType() {
        return estimationLineType;
    }

    public void setEstimationLineType(EstimationLineType estimationLineType) {
        this.estimationLineType = estimationLineType;
    }

    public BigDecimal getQuantity() {
        return quantity == null?new BigDecimal("0.00"):quantity;

    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public int getComplexFactoryCharge() {
        return complexFactoryCharge ;
    }

    public void setComplexFactoryCharge(int complexFactoryCharge) {
        this.complexFactoryCharge = complexFactoryCharge;
    }
//    @Depends("functionality.functionLabel,quantity,complexFactoryCharge") 
//    public BigDecimal getEstimated() {
//        if(getFunctionality() != null && getQuantity()!= new BigDecimal("0.00")
//                && getComplexFactoryCharge()!=0) {
//        Functionality func = getManager().find(Functionality.class, functionality.getFunctionLabel());
//        return getQuantity().multiply(new BigDecimal(getComplexFactoryCharge())).multiply(func.getBaAnalysis());}
//        return BigDecimal.ZERO;
//    }

    public Functionality getFunctionality() {
        return functionality;
    }

    public void setFunctionality(Functionality functionality) {
        this.functionality = functionality;
    }

}