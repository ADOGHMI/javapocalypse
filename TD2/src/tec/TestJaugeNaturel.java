package tec;
import tec.*;

public class TestJaugeNaturel {
    private JaugeNaturel jauge;
    public TestJaugeNaturel() {
	this.jauge = new JaugeNaturel(67899, 100);
    }
    public void testDansIntervalle() {
	assert this.jauge.estBleu() == false;
	assert this.jauge.estVert() == true;
	assert this.jauge.estRouge() == false;
    }
}

