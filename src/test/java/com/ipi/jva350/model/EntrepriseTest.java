package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntrepriseTest {

    @Test
    public void testEstDansPlage() {
        // Given
        LocalDate d = LocalDate.of(2024, 2, 15);
        LocalDate debut = LocalDate.of(2024, 2, 10);
        LocalDate fin = LocalDate.of(2024, 2, 20);
        // When
        boolean result = Entreprise.estDansPlage(d, debut, fin);
        // Then
        assertEquals(true, result);
    }
    @Test
    public void testEstDansAvantPlage() {
        // Given
        LocalDate d = LocalDate.of(2024, 2, 5);
        LocalDate debut = LocalDate.of(2024, 2, 10);
        LocalDate fin = LocalDate.of(2024, 2, 20);
        // When
        boolean result = Entreprise.estDansPlage(d, debut, fin);
        // Then
        assertEquals(false, result);
    }
    @Test
    public void testEstDansApresPlage() {
        // Given
        LocalDate d = LocalDate.of(2024, 2, 25);
        LocalDate debut = LocalDate.of(2024, 2, 10);
        LocalDate fin = LocalDate.of(2024, 2, 20);
        // When
        boolean result = Entreprise.estDansPlage(d, debut, fin);
        // Then
        assertEquals(false, result);
    }

    @Test
    public void testEstJourFerie() {
        Entreprise entreprise = new Entreprise();
        // Supposons que l'entreprise a une méthode pour ajouter des jours fériés
        entreprise.ajouterJourFerie(LocalDate.of(2024, Month.JANUARY, 1)); // Nouvel An
        entreprise.ajouterJourFerie(LocalDate.of(2024, Month.MAY, 1)); // Fête du Travail

        assertTrue(entreprise.estJourFerie(LocalDate.of(2024, Month.JANUARY, 1))); // Nouvel An
        assertTrue(entreprise.estJourFerie(LocalDate.of(2024, Month.MAY, 1))); // Fête du Travail

    }

}
