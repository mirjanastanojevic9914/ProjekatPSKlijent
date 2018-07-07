/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.doctor.model;

import domain.Doctor;
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
public class DoctorTableModel extends AbstractTableModel{
    private List<GenericDomainObject> listDoctors;
    private String[] columns = {"ID:", "Name:", "Surname:", "Gender:", "Date birth:", "Mobile number:", "Date employment:", "Salary:", "Doctor type"};

    public DoctorTableModel(List<GenericDomainObject> listDoctors) {
        this.listDoctors = listDoctors;
    }

    
    
    @Override
    public int getRowCount() {
        if (listDoctors == null) {
            return 0;
        }
        return listDoctors.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        GenericDomainObject doctor = listDoctors.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return doctor.get("id");
            case 1:
                return doctor.get("name");
            case 2:
                return doctor.get("surname");
            case 3:
                return doctor.get("gender");
            case 4:
                String dateBirth = sdf.format(doctor.get("dateBirth"));
                return dateBirth;
            case 5:
               return doctor.get("mobileNumber");
            case 6:
                String dateEmployment = sdf.format(doctor.get("dateEmployment"));
                return dateEmployment;
            case 7:
                return doctor.get("salary");
            case 8:
                return doctor.get("doctorType");
            
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    public Doctor getDoctor(int rowIndex) {
        return (Doctor) listDoctors.get(rowIndex);
    }
}
