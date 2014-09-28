
class TestEtatPassager{

    void testExterieur(){
        EtatPassager dh = new EtatPassager();
        assert dh.estAssis() == false;
        assert dh.estDebout() == false;
        assert dh.estExterieur();
        assert dh.estInterieur() == false;
    }
    void testAssis(){
        EtatPassager dh = new EtatPassager();
        EtatPassager as = dh.assis();
        assert as.estAssis();
        assert as.estDebout() == false;
        assert as.estExterieur() == false;
        assert as.estInterieur();
    }
    void testDebout(){
        EtatPassager dh = new EtatPassager();
        EtatPassager de = dh.debout();
        assert de.estAssis() == false;
        assert de.estDebout();
        assert de.estExterieur() == false;
        assert de.estInterieur();
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
