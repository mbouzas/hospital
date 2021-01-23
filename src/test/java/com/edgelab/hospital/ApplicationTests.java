package com.edgelab.hospital;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ApplicationTests {

    Hospital hospitalSimulator = new Hospital();

    @Test
    void contextLoads() {
    }


    @Test
        public void testNoTreatment(){
        Map<String, Integer> test =  new HashMap<>();
        test = hospitalSimulator.frequencyPatientStatus("F,D");
        test = hospitalSimulator.treatmentPatients (test, "");
        Assert.assertEquals(test.entrySet().toString(),"[D=0, T=0, F=1, H=0, X=1]");
    }

    @Test
    public void testMultipleStatesInsulineAntibiotic(){
        Map<String, Integer> test =  new HashMap<>();
        test = hospitalSimulator.frequencyPatientStatus("D,D,F,H");
        test = hospitalSimulator.treatmentPatients (test, "I,An");
        Assert.assertEquals(test.entrySet().toString(),"[D=2, T=0, F=2, H=0, X=0]");
    }

    @Test
    public void testFeverParacetamol() {
        Map<String, Integer> test =  new HashMap<>();
        test = hospitalSimulator.frequencyPatientStatus("F");
        test = hospitalSimulator.treatmentPatients(test, "P");
        Assert.assertEquals(test.entrySet().toString(), "[D=0, T=0, F=0, H=1, X=0]");
    }

    @Test
    public void testFeverParacemoltAspirin() {
        Map<String, Integer> test =  new HashMap<>();
        test = hospitalSimulator.frequencyPatientStatus("F,H,H");
        test = hospitalSimulator.treatmentPatients(test, "P,As");
        Assert.assertTrue(test.entrySet().toString().equals( "[D=0, T=0, F=0, H=0, X=3]") || test.entrySet().toString().equals( "[D=0, T=0, F=0, H=1, X=2]") );
    }

    @Test
    public void testMultipleScenario() {
        Map<String, Integer> test =  new HashMap<>();
        test = hospitalSimulator.frequencyPatientStatus("F,F,H,F,T,X,D,D,H,F,T,T,H,H,X,T,F,F,D,T,X,F");
        test = hospitalSimulator.treatmentPatients(test, "P,An,I");
        Assert.assertTrue(test.entrySet().toString().equals( "[D=3, T=0, F=4, H=12, X=3]") || test.entrySet().toString().equals( "[D=3, T=0, F=4, H=13, X=2]") );
    }

}
