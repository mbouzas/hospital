package com.edgelab.hospital;

import java.util.Map;
import java.util.SplittableRandom;

/**
 * One time in a million the Flying Flying Spaghetti Monster shows his noodly power
 * and resurrects a dead patient (Dead becomes Healthy).
 */
public class  FlyingFlyingSpaghettiMonster {

    /**
     * Method  once the probability is achieved, a patient is resuscitated
     * probability (One time in a million)
     * @param patientsStatusFinal
     * @return Map patientsStatusFinal.
     */
    public static Map<String, Integer> getFlyingFlyingSpaghettiMonster(Map<String, Integer> patientsStatusFinal) {
        SplittableRandom random = new SplittableRandom();
        boolean whoKnows = random.nextInt(1, 1000001) <= 1;
        if (whoKnows && patientsStatusFinal.get("X") > 0) {
            patientsStatusFinal.put("H", patientsStatusFinal.get("H") + 1);
            patientsStatusFinal.put("X", patientsStatusFinal.get("X") - 1);
        }
        return patientsStatusFinal;
    }
}
