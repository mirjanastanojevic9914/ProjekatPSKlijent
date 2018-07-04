/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.patient.model;

import domain.GenericDomainObject;
import domain.Patient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mira
 */
public class PatientTableModel extends AbstractTableModel{
     private List<GenericDomainObject> listPatient;
    private String[] columns = {"ID:", "Name:", "Surname:", "Gender:", "Date birth:", "Mobile number:"};

    public PatientTableModel(List<GenericDomainObject> patients) {
        this.listPatient = patients;
    }
    
    @Override
    public int getRowCount() {
        if (listPatient == null) {
            return 0;
        }
        return listPatient.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        GenericDomainObject patient = listPatient.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return patient.get("id");
            case 1:
                return patient.get("name");
            case 2:
                return patient.get("surname");
            case 3:
                return patient.get("gender");
            case 4:
                String dateBirth = sdf.format(patient.get("dateBirth"));
                return dateBirth;
            case 5:
               return patient.get("mobileNumber");
            
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    public Patient getPatient(int rowIndex) {
        return (Patient) listPatient.get(rowIndex);
    }
}
