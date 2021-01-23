package com.edgelab.hospital;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        if (args == null || args.length < 1)
            throw new IllegalArgumentException("invalid arguments, add  for examples Patients F,D  and Drugs P");

        Hospital hospitalSimulator = new Hospital();
        if (args.length==1) {
            hospitalSimulator.treatmentPatients(hospitalSimulator.frequencyPatientStatus(args[0]),"").forEach((key, value) -> System.out.println(key + ":" + value));
        }else{
            hospitalSimulator.treatmentPatients(hospitalSimulator.frequencyPatientStatus(args[0]),args[1]).forEach((key, value) -> System.out.println(key + ":" + value));
        }
    }
}


