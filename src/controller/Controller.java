/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.CommunicationServer;
import domain.Doctor;
import domain.DoctorType;
import domain.GenericDomainObject;
import domain.Patient;
import java.util.ArrayList;
import java.util.List;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;
import transfer.util.EnumResponseStatus;
import transfer.util.IOperation;

/**
 *
 * @author Mira
 */
public class Controller {
    private static Doctor currentDoctor;
    private static Controller instanca;

    public Controller() {
    }

    
    public static Controller getInstanca() {
        if (instanca == null) {
            instanca = new Controller();
        }
        return instanca;
    }

    
    public void setCurrentDoctor(Doctor doctor) {
        currentDoctor = doctor;
 
    }

    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }
    
    
    public Doctor loginDoctor(Doctor doctor) throws Exception {
        
        Doctor objectRequest = doctor;
        ResponseObject responseObject = sendRequest(IOperation.LOGIN_DOCTOR, objectRequest);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return (Doctor) responseObject.getResponse();
        }
        throw new Exception("Combination of atributtes incorrect!");
    }
    
    
    private ResponseObject sendRequest(int action, Object request) throws Exception {
        try {
            RequestObject requestObject = new RequestObject();
            requestObject.setAction(action);
            if (request != null) {
                requestObject.setRequest(request);
            }
            
            CommunicationServer.getInstanca().sendRequest(requestObject);
            ResponseObject responseObject = CommunicationServer.getInstanca().getResponse();
            
            return responseObject;
        } catch (Exception ex) {
//            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public String savePatient(Patient patient) throws Exception {
         ResponseObject responseObject = sendRequest(IOperation.SAVE_PATIENT, patient);
        


        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return "Succesfully saved patient!";
        }
        throw new Exception("Error, patient can't be saved!");
    }
    
    
    

    public String updatePatient(Patient patient) throws Exception {
         ResponseObject responseObject = sendRequest(IOperation.UPDATE_PATIENT, patient);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return "Succesfully updated patient!";
        }
        throw new Exception("Error while updating patient!");
    }

    public List<GenericDomainObject> getPatientsWithCriteria(String criteria) throws Exception{
        List<Object> parametars = new ArrayList<>();
        parametars.add(criteria);
        parametars.add(new Patient());

        ResponseObject responseObject = sendRequest(IOperation.GET_PATIENTS_WITH_CRITERIA, parametars);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<GenericDomainObject> gdoList = (ArrayList<GenericDomainObject>) responseObject.getResponse();

            return gdoList;
        }
        throw new Exception("There are no patients with that critetia! ");
    }

    public Patient getPatient(Patient p) throws Exception{
         ResponseObject responseObject = sendRequest(IOperation.FIND_PATIENT_FOR_DETAILS, p);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            
            Patient patient = (Patient) responseObject.getResponse();
            return patient;
        }
        throw new Exception("Datas of the patient have not found!");
    }

    public ArrayList<DoctorType> getDoctorTypesForCB() throws Exception{
         ResponseObject responseObject = sendRequest(IOperation.GET_LIST_DOCTOR_TYPES_CB, null);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<GenericDomainObject> gdoList = (ArrayList<GenericDomainObject>) responseObject.getResponse();
            ArrayList<DoctorType> list = new ArrayList<>();
            for (GenericDomainObject gdo : gdoList) {
                list.add((DoctorType) gdo);
            }
            return list;
        }
        throw new Exception("Error while getting list of doctor types");
    }

    public String saveDoctor(Doctor doctor) throws Exception{
         ResponseObject responseObject = sendRequest(IOperation.SAVE_DOCTOR, doctor);
        


        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return "Succesfully saved doctor!";
        }
        throw new Exception("Error, doctor can't be saved!");
    }

    public Doctor getDoctor(Doctor d) throws Exception {
         ResponseObject responseObject = sendRequest(IOperation.FIND_DOCTOR_FOR_DETAILS, d);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            
            Doctor doctor = (Doctor) responseObject.getResponse();
            return doctor;
        }
        throw new Exception("Datas of the doctor have not found!");
    }

    public String updateDoctor(Doctor doctor)  throws Exception{
         ResponseObject responseObject = sendRequest(IOperation.UPDATE_DOCTOR, doctor);

        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            return "Succesfully updated doctor!";
        }
        throw new Exception("Error while updating doctor!");
    }

    public List<GenericDomainObject> getDoctorsWithCriteria(String criteria)  throws Exception{
         List<Object> parametars = new ArrayList<>();
        parametars.add(criteria);
        parametars.add(new Doctor());

        ResponseObject responseObject = sendRequest(IOperation.GET_DOCTORS_WITH_CRITERIA, parametars);
        if (responseObject.getResponseStatus() == EnumResponseStatus.OK) {
            List<GenericDomainObject> gdoList = (ArrayList<GenericDomainObject>) responseObject.getResponse();

            return gdoList;
        }
        throw new Exception("There are no doctors with that critetia! ");
    }

    
    
    
}
