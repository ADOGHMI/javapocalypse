package tec;
import tec.*;

public class TestJaugeNaturel {
    private JaugeNaturel jauge;

    public TestJaugeNaturel() {
    }

    public void lancerTests() {
        this.testIncrementer();
        this.testDecrementer();
    }

    public void testDansIntervalle() {
        this.testCouleur(true, true, false);
    }

    public void testDepartSuperieurVigie() {
        this.testCouleur(false, false, true);
    }

    public void testDepartEgalVigie() {
        this.testCouleur(false, true, true);
    }

    public void testDepartNegatif() {
        this.testCouleur(false, true, false);
    }

    public void testDepartZero() {
        this.testCouleur(true, true, false);
    }

    public void testIncrementer() {
        this.newJaugeNaturel(-1, 2);
        this.testDepartNegatif();
        this.incrementer();
        this.testDepartZero();
        this.incrementer();
        this.testDansIntervalle();
        this.incrementer();
        this.testDepartEgalVigie();
        this.incrementer();
        this.testDepartSuperieurVigie();
    }

    public void testDecrementer() {
        this.newJaugeNaturel(3, 2);
        this.testDepartSuperieurVigie();
        this.decrementer();
        this.testDepartEgalVigie();
        this.decrementer();
        this.testDansIntervalle();
        this.decrementer();
        this.testDepartZero();
        this.decrementer();
        this.testDepartNegatif();
    }

    private void incrementer() {
        this.jauge.incrementer();
    }

    private void decrementer() {
        this.jauge.decrementer();
    }

    private void newJaugeNaturel(int depart, int maxVigie) {
        this.jauge = new JaugeNaturel(maxVigie, depart);
        System.out.println(this.jauge.toString());
    }

    private void testCouleur(boolean vert, boolean bleu, boolean rouge) {
        assert this.jauge.estVert() == vert;
        assert this.jauge.estBleu() == bleu;
        assert this.jauge.estRouge() == rouge;
    }
}
