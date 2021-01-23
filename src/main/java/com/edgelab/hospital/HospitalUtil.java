package com.edgelab.hospital;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Generic operations for the Hospital.
 */
public class HospitalUtil {

    /**
     * Enum with the different patient state ->arg0
     */
    public enum PatientState {
        Fever ("F"),
        Diabetes ("D"),
        Healthy("H"),
        Turberculosis ("T"),
        Dead ("X");

        private String status;
        PatientState(String status) {
            this.status = status;
        }
    }

    /**
     * Enum with the different drugs  to apply ->arg1
     */
    public enum Drugs {
        Aspirin ("As"),
        Antibiotic ("An"),
        Insulin ("I"),
        Paracetamol ("P"),
        NoTreatment ("");

        private String drug;
        Drugs(String drug) {
            this.drug = drug;
        }
    }

    /**
     * Method  to validate the arg0, throwing an exception if there is an invalidate patient state.
     * @param patientstatus
     * @return List patientstatusList.
     */
    public static List<String> getValidatePatientStatus(String patientstatus) {
        patientstatus.trim();
        List<String> patientstatusList = Arrays.asList(patientstatus.trim().split(","));
        for(String c : patientstatusList) {
            Arrays.stream(PatientState.values()).filter(f -> f.status.equals(c)).findAny().orElseThrow(IllegalArgumentException::new);
        }
        return patientstatusList;
    }

    /**
     * Method  to validate the arg1, throwing an exception if there is an invalidate drug.
     * @param drugsPatience
     * @return List drugsPatienceList.
     */
    public static List<String> getValidateDrugs(String drugsPatience) {
        drugsPatience.trim();
        List<String> drugsPatienceList = Arrays.asList(drugsPatience.split(","));
        for(String c : drugsPatienceList) {
            Arrays.stream(Drugs.values()).filter(f -> f.drug.equals(c)).findAny().orElseThrow(IllegalArgumentException::new);
        }
        return drugsPatienceList;
    }

    /**
     * Method  to initialze patient states to 0 if no value previously..
     * @param status
     */
    public static Map<String, Integer> initPatientStatusToZero(Map<String, Integer> status) {
        for (PatientState p : PatientState.values()) {
            if (status.get(p.status)==null){
                status.put(p.status,0);
            }
        }
        return status;
    }
}


