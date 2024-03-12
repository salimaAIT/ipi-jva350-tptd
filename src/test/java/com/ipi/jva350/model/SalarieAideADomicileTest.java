package com.ipi.jva350.model;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)


public class SalarieAideADomicileTest {


    // Tests unitaires simples
    // il faut JoursTravaillesAnneeNMoins1() >= 10
    @Test // indique que sera exécutée par Junit
    public void testALegalementDroitADesCongesPayesTrue() {
        // Given : Mise en place de l'environnement du test et de ses données (hypothèses)
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023, 6, 28),
                LocalDate.now(),
                20,
                5,
                120,
                15,
                8);
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        assertEquals(true, res, "joursTravaillesAnneeNMoins1 doit etre supérieur à 10");
    }

    // il faut JoursTravaillesAnneeNMoins1() < 10
    @Test // indique que sera exécutée par Junit
    public void testALegalementDroitADesCongesPayesFalse() {
        // Given : Mise en place de l'environnement du test et de ses données (hypothèses)
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023, 6, 28),
                LocalDate.now(),
                20,
                5,
                9,
                15,
                8);
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        boolean res = monSalarie.aLegalementDroitADesCongesPayes();
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        assertEquals(false, res, "joursTravaillesAnneeNMoins1 doit etre supérieur à 10");
    }


   /* @Test
    @ParameterizedTest
    public void testCalculeJoursDeCongeDecomptesPourPlage() {
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023, 6, 28),
                LocalDate.now(),
                20,
                5,
                9,
                15,
                8);
        // When : Comportement à tester, en pratique une (ou des) méthode(s) à exécuter
        LinkedHashSet<LocalDate> res = monSalarie.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2023, 12, 17),
                LocalDate.of(2023, 12, 28));
        // Then : Comparaison du résultat de la méthode ou de l'état final avec celui attendu
        assertEquals(9, res.size());
    }*/


    //Tests paramétrés

    @ParameterizedTest
    @CsvSource({
            "'2023-12-17','2023-12-28',9",
            "'2023-12-17','2024-01-08',17",
    })
    void testCalculeJoursDeCongeDecomptesPourPlage(String dateDebut, String dateFin, int expectedNb) {
        SalarieAideADomicile monSalarie = new SalarieAideADomicile(
                "Léa",
                LocalDate.of(2023, 6, 28),
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
        assertEquals(expectedNb, resNb.size());
    }


    @Mock
    private SalarieAideADomicileRepository salarieAideADomicileRepository;
    @InjectMocks
    private SalarieAideADomicileService salarieService = new SalarieAideADomicileService();


    @ExtendWith(MockitoExtension.class)
    public class SalarieAideADomicileServiceMockTest {

        @Mock
        private SalarieAideADomicileRepository salarieAideADomicileRepository;
        @InjectMocks
        private SalarieAideADomicileService salarieService = new SalarieAideADomicileService();

        // mocké :
        @Test
        public void testAjouteConge() throws SalarieException {
            // Given :
            SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                    LocalDate.of(2022, 6, 28), LocalDate.of(2023, 11, 1),
                    9, 2.5,
                    80, 20, 8);
            // When :
            salarieService.ajouteConge(monSalarie, LocalDate.of(2024, 12, 17),
                    LocalDate.of(2024, 12, 18));
            // Then :
            ArgumentCaptor<SalarieAideADomicile> salarieAideADomicileCaptor = ArgumentCaptor.forClass(SalarieAideADomicile.class);
            Mockito.verify(salarieAideADomicileRepository, Mockito.times(1)).save(salarieAideADomicileCaptor.capture()); // arg capture !
            Assertions.assertEquals(1L, salarieAideADomicileCaptor.getValue().getCongesPayesPrisAnneeNMoins1());
        }

    }
}


