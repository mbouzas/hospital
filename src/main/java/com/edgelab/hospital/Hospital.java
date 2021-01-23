package com.edgelab.hospital;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Simulator of a Hospital to give a treatment to the patients
 */
public class Hospital {

    /**
     * Method  to validate the arg0, throwing an exception if there is an invalidate patient state,
     * and getting the frequency of the patients by state
     * @param patientState
     * @return Map statusPatienceFrequency.
     */
        public  Map<String, Integer>  frequencyPatientStatus(String patientState) {
        List<String> statusPatienceFrequency = HospitalUtil.getValidatePatientStatus(patientState);
        return statusPatienceFrequency.stream().collect(Collectors.toMap(Function.identity(), c -> 1, Math::addExact));
    }

    /**
     * Method  to apply the treatment(drugs) to the patients applying the Engine Rules.
     * @param status
     * @param drugsPatience
     * @return Map statusFinal
     */
    public  Map<String, Integer> treatmentPatients(Map<String, Integer> status, String  drugsPatience) {
        List<String> drugsPatient = HospitalUtil.getValidateDrugs(drugsPatience);
        // define facts
        Facts facts = new Facts();
        drugsPatient.forEach(drug -> facts.put(drug, "true"));
        status.entrySet().forEach((k) -> facts.put(k.getKey(),true));

        status = HospitalUtil.initPatientStatusToZero(status);
        Map<String, Integer> statusFinal = new HashMap<>();
        statusFinal = HospitalUtil.initPatientStatusToZero(statusFinal);
        statusFinal.put("X",status.get("X")!=null? status.get("X"):0);
        Integer totalPatience =  status.values().stream().reduce(0, Integer::sum);
        runningRules(status, facts, totalPatience, statusFinal);
        return statusFinal;
    }

    /**
     * Method  to validate the arg0, throwing an exception if there is an invalidate patient state.
     * @param status
     * @param facts
     * @param totalPatience
     * @param statusFinal
     * @return Map statusFinal
     */
    private static void runningRules(Map<String, Integer> status, Facts facts, Integer totalPatience, Map<String, Integer> statusFinal) {
        Rules rules = com.edgelab.hospital.RulesEngine.getRules(status, facts, totalPatience, statusFinal);
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
        FlyingFlyingSpaghettiMonster.getFlyingFlyingSpaghettiMonster(statusFinal);
    }
}
