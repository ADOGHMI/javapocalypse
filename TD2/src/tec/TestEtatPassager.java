package tec;
import tec.*;

public class TestEtatPassager {
    public TestEtatPassager(){};
    void testExterieur(){
	EtatPassager dh = new EtatPassager(); 
	assert dh.estAssis() == false;
	assert dh.estDebout() == false;
	assert dh.estExterieur();
	assert dh.estInterieur() == false;
    }
    void testAssis(){
	EtatPassager dh = new EtatPassager();
	assert dh.assis().estAssis();
	assert dh.assis().estDebout() == false;
	assert dh.assis().estExterieur() == false;
	assert dh.assis().estInterieur();
    }
    void testDebout(){
	EtatPassager dh = new EtatPassager();
	EtatPassager db = dh.debout();
	assert db.estAssis() == false;
	assert db.estDebout();
	assert db.estExterieur() == false;
	assert db.estInterieur();
    }
    void lancer(){
	int i = 0;
	System.out.println(".");
	this.testExterieur();
	i++;
	System.out.println(".");
	this.testAssis();
	i++;
	System.out.println(".");
	this.testDebout();
	i++;
	System.out.println("OK "+i);
    }
}
