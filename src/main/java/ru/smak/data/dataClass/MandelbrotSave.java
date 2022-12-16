package ru.smak.data.dataClass;

public class MandelbrotSave {
    public int maxIterations;
    public double r;
    public MandelbrotSave(Integer maxIterations,Double r)
    {
        this.maxIterations= maxIterations;
        this.r= r;

    }
    public  Integer maxIterations()
    {
        return  maxIterations;
    }
    public  Double r()
    {
        return  r;
    }
    @Override
    public String toString()
    {

        return "MandelbrotSave{"+"MaxIterations='"+maxIterations+'\''
                + ", r='"+ r +'}';
    }
}
