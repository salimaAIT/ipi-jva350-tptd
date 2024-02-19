package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class SalarieAideADomicileTest {


                          // Tests unitaires simples
    // il faut JoursTravaillesAnneeNMoins1() >= 10
    @Test // indique que sera exécutée par Junit
    public void testALegalementDroitADesCongesPayesTrue(){
        // Given : Mise en place de l'environnement du test et de ses données (hypothèses)
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                5,
                120,
                15,
                8);
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        Assertions.assertEquals(true,res, "joursTravaillesAnneeNMoins1 doit etre supérieur à 10");
    }

    // il faut JoursTravaillesAnneeNMoins1() < 10
    @Test // indique que sera exécutée par Junit
    public void testALegalementDroitADesCongesPayesFalse(){
        // Given : Mise en place de l'environnement du test et de ses données (hypothèses)
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023,6,28),
                LocalDate.now(),
                20,
                5,
                9,
                15,
                8);
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        Assertions.assertEquals(false,res, "joursTravaillesAnneeNMoins1 doit etre supérieur à 10");
    }





                            //Tests paramétrés
 /*   @Test
    public void testCalculeJoursDeCongeDecomptesPourPlage(String numeroSecu, Boolean expected) {
            // Given, When, Then
            Assertions.assertEquals(expected, Patient.checkNumeroSecu(numeroSecu));
        }

*/
}
