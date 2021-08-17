package Factories;

import MainDomain.Pointable;

public abstract class AddablePartFactory implements BattleFactory {
        private Pointable p;
        private int addablePartType;

    public AddablePartFactory(Pointable p,int addablePartType) {
        this.p=p;
        this.addablePartType=addablePartType;

    }

    public Pointable getP() {
        return p;
    }

    public int getAddablePartType() {
        return addablePartType;
    }

    public void setAddablePartType(int addablePartType) {
        this.addablePartType = addablePartType;
    }


    public void setP(Pointable p) {
        this.p = p;
    }

    public abstract Pointable createBattleProduct() ;
        

}
