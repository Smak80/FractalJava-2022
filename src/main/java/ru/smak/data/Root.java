package ru.smak.data;

import ru.smak.data.dataClass.ColorSave;
import ru.smak.data.dataClass.MandelbrotSave;
import ru.smak.data.dataClass.PlaneSave;

public class Root
{
    public Integer MandelbrotXi;
    public Integer CurrentColorI;
    public ColorSave ColorSave;
    public PlaneSave PlaneSave;
    public MandelbrotSave MandelbrotSave;
    public  Boolean MaxIterations;
    public  Integer getMandelbrotXi()
    {
        return  MandelbrotXi;
    }
    public  Integer getCurrentColorI()
    {
        return  CurrentColorI;
    }
    public  ColorSave getColorSave()
    {
        return  ColorSave;
    }
    public PlaneSave getPlaneSave() {return  PlaneSave;}
    public MandelbrotSave getMandelbrotSave() {return  MandelbrotSave;}
    public Boolean getMaxIterationsSave() {return  MaxIterations;}

    public void setMandelbrotXi(Integer MandelbrotXi)
    {
        this.MandelbrotXi = MandelbrotXi;
    }

    public void setCurrentColorI(Integer CurrentColorI)
    {
        this.CurrentColorI = CurrentColorI;
    }
    public  void setColorSave(ColorSave ColorSave)
    {
        this.ColorSave = ColorSave;
    }
    public  void setPlaneSave(PlaneSave PlaneSave)
    {
        this.PlaneSave= PlaneSave;
    }
    public  void setMandelbrotSave(MandelbrotSave MandelbrotSave)
    {
        this.MandelbrotSave = MandelbrotSave;
    }
    public  void setMaxIterationsSave(Boolean MaxIterationsSave)
    {
        this.MaxIterations = MaxIterationsSave;
    }

    @Override
    public String toString()
    {

        return "Root{"+"MandelbrotXi='"+MandelbrotXi+'\''
                + ", CurrentColorI='"+ CurrentColorI  +'\''
                + ", ColorSave='"+ ColorSave +'\''
                + ", PlaneSave='"+ PlaneSave +'\''
                + ", MandelbrotSave='"+ MandelbrotSave + '\''
                + ", MaxIterationsSave='"+ MaxIterations + '\'' + '}';
    }
}