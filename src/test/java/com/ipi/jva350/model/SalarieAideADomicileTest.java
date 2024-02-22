package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;


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





 @Test
 @ParameterizedTest
  public void testCalculeJoursDeCongeDecomptesPourPlage()  {
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
                    LinkedHashSet<LocalDate> res = monSalarie.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2023,12,17),
                    LocalDate.of(2023,12,28));
                    // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
                    Assertions.assertEquals(9,res.size());
                            }


                        //Tests paramétrés

    @ParameterizedTest
    @CsvSource({
            "'2023-12-17','2023-12-28',9",
            "'2023-12-17','2024-01-08',17",
    })
     void testCalculeJoursDeCongeDecomptesPourPlage(String dateDebut,String dateFin, int expectedNb)  {
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
      LinkedHashSet<LocalDate> resNb = monSalarie.calculeJoursDeCongeDecomptesPourPlage(
              LocalDate.parse(dateDebut),
              LocalDate.parse(dateFin));
      // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
      Assertions.assertEquals(expectedNb,resNb.size());
                        }
}
