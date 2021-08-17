package AddablePartsDomain;

import MainDomain.Pointable;


public abstract  class AddablePart  /* Decorator */ extends Pointable {
    Pointable p;

    public AddablePart(int startRange,int endRange, Pointable p) {
    super(startRange,endRange);
    this.p=p;
    setType(p.getType());

    }


    public Pointable getP() {
    return p;
    }

    public void setP(Pointable newP) {
    this.p=newP;
    }

    public int getPoint() {
    return p.getPoint() + super.getPoint();

    }

    public void resetPoint() {
        super.resetPoint();
        p.resetPoint();
    }

    public  void setPointAccordingToRange()
    {
        super.setPointAccordingToRange();
          p.setPointAccordingToRange(); // setting the its Pointable object's point.

    }

    public String toString() {
        StringBuilder currentString=new StringBuilder(p.toString());
        int l=currentString.length();
        char c= currentString.charAt(l-1);
        if (c == (')')) {
            currentString.setCharAt(l-1,',');

        } else {
            currentString.append( " (");
        }
        return currentString.toString();
    }


    public String pointString() {
        return super.toString();
    }

}

