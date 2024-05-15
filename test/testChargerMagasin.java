


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import donnees.Magasin;

import XML.ChargeurMagasin;

public class TestChargeurMagasin {

    private ChargeurMagasin chargeur;

    @Before
    public void setUp() {
        // Initialisation du chargeur avec un répertoire valide
        chargeur = new ChargeurMagasin("repertoire_valide");
    }

    @Test
    public void testChargerMagasinAvecRepertoireValide() {
        try {
            Magasin magasin = chargeur.chargerMagasin();
            // Vérifie que le magasin contient le bon nombre de CDs
            assertEquals(3, magasin.getNbCDs());
        } catch (FileNotFoundException e) {
            fail("Le répertoire valide n'a pas été trouvé.");
        } catch (IOException e) {
            fail("Une exception inattendue a été levée : " + e.getMessage());
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void testChargerMagasinAvecRepertoireInvalide() throws FileNotFoundException {
        // Initialisation du chargeur avec un répertoire inexistant
        chargeur = new ChargeurMagasin("repertoire_invalide");
        // Doit lever une FileNotFoundException
        chargeur.chargerMagasin();
    }

}
