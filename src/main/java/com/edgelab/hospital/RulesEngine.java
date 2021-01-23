package com.edgelab.hospital;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.RuleBuilder;

import java.util.Map;

/**
 * Class to register Java rules engine, all the condictions.
 * Created rules with conditions and actions, and registered
 */
public class RulesEngine {


    /**
     * Method  To Create rules with conditions and actions, and register them
     * probability (One time in a million)
     * @param facts
     * @param totalPatience
     * @param statusFinal
     * @return Rules registered.
     */
    public static Rules getRules(Map<String, Integer> status, Facts facts, Integer totalPatience, Map<String, Integer> statusFinal) {
        Rule feverRule = new RuleBuilder()
                .name("Aspirin cures Fever")
                .description("if a pacient with Fever is treated with Aspirin -> pacient will be Healthy ")
                .when(fact -> facts.get("F")!=null && facts.get("As")!=null)
                .then(fact -> statusFinal.put("H",status.get("H") +status.get("F")))
                .then(fact -> statusFinal.put("F",0))
                .then(fact -> status.put("F",0))
                .build();

        Rule feverRule2 = new RuleBuilder()
                .name("Paracetamol cures Fever")
                .description("if a pacient with Fever is treated with Paracetamol -> pacient will be Healthy ")
                .when(fact -> facts.get("F")!=null && facts.get("P")!=null)
                .then(fact -> statusFinal.put("H",statusFinal.get("H") != null ? statusFinal.get("H") + status.get("F"):status.get("F") ))
                .then(fact -> statusFinal.put("F",0))
                .then(fact -> status.put("F",0))
                .build();

        Rule insulineAndAntibioticRule = new RuleBuilder()
                .name("Z Insulin is mixed with Antibiotic")
                .description("Insulin mixed Antibiotic, healthy people catch Fever")
                .when(fact -> facts.get("I")!=null && facts.get("An")!=null && facts.get("H")!=null)
                .then(fact -> statusFinal.put("F", (status.get("F") > 0) ? status.get("F") + status.get("H"):status.get("H")))
                 .then(fact -> statusFinal.put("H",statusFinal.put("H",statusFinal.get("H")-statusFinal.get("F"))))
                .build();

        Rule tuberculosisRule = new RuleBuilder()
                .name("Antibiotic cures Tuberculosis")
                .description("if a pacient with Tuberculosis is treated with Antibiotic -> pacient will be Healthy ")
                .when(fact -> facts.get("T")!=null && facts.get("An")!=null)
                .then(fact -> statusFinal.put("H",statusFinal.get("H") != null ? statusFinal.get("H") +status.get("T"):status.get("T")))
                .then(fact -> status.put("T",0))
                .build();

  /*      Rule insulinRule = new RuleBuilder()
                .name("Insulin prevents Diabetic 1")
                .description("Insulin prevents diabetic subject from dying, does not cure Diabetes")
                .when(fact -> facts.get("D")!=null && !drugsPatience2.contains("I"))
                .then(fact -> status2.put("X",status.get("D")))
                .then(fact -> status2.put("D",0))
                //.then(fact -> status2.put("D",0))
                .build();*/

        Rule insulinRule = new RuleBuilder()
                .name("Insulin prevents Diabetic")
                .description("Insulin prevents diabetic subject from dying, does not cure Diabetes")
                .when(fact -> facts.get("D")!=null && facts.get("I")!=null)
                .then(fact -> statusFinal.put("D",status.get("D")))
                //.then(fact -> status2.put("D",0))
                .build();

        Rule paracetamolAndAspirinRule = new RuleBuilder()
                .name("Parecetamol is mixed with Aspirin")
                .description("Paracetamol kills subject if mixed with aspirin")
                .when(fact -> facts.get("P")!=null && facts.get("As")!=null)
                .then(fact -> statusFinal.put("X",totalPatience))
                .then(fact -> statusFinal.put("F",0))
                .then(fact -> statusFinal.put("H",0))
                .then(fact -> statusFinal.put("T",0))
                .then(fact -> statusFinal.put("D",0))
                .build();

        Rule noTreatment =  new RuleBuilder()
                .name("no treatment")
                .description("No treatment given")
                .when(fact -> facts.get("")!=null)
                .then(fact -> statusFinal.putAll(status))
                .then(fact -> statusFinal.put("D",0))
                .then(fact -> statusFinal.put("X",status.get("D")))
                .build();

        Rules rules = new Rules();
        rules.register(feverRule);
        rules.register(tuberculosisRule);
        rules.register(feverRule2);
        rules.register(insulinRule);
        rules.register(insulineAndAntibioticRule);
        rules.register(paracetamolAndAspirinRule);
        rules.register(noTreatment);
        return rules;
    }
}
