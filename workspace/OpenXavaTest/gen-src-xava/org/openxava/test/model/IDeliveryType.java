

// File generated by OpenXava: Mon Nov 04 09:58:11 CET 2019
// Archivo generado por OpenXava: Mon Nov 04 09:58:11 CET 2019

// WARNING: NO EDIT
// OJO: NO EDITAR
// Component: DeliveryType		Java interface for entity/Interfaz java para Entidad

package org.openxava.test.model;

import java.math.*;
import java.rmi.RemoteException;


public interface IDeliveryType  extends org.openxava.model.IModel {	

	// Properties/Propiedades 	
	public static final String PROPERTY_number = "number"; 	
	int getNumber() throws RemoteException; 	
	public static final String PROPERTY_description = "description"; 
	String getDescription() throws RemoteException;
	void setDescription(String description) throws RemoteException;	

	java.util.Collection getDeliveries() throws RemoteException;		

	// References/Referencias

	// Methods 


}