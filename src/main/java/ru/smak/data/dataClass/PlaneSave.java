package ru.smak.data.dataClass;

public class PlaneSave
{
    public double _xMin;
    public double _xMax;
    public double _yMin;
    public double _yMax;
    public int _width;
    public int _height;
    public PlaneSave(Double _xMin,Double _xMax,Double _yMin ,Double _yMax,Integer _width,Integer _height)
    {
        this._xMin= _xMin;
        this._xMax= _xMax;
        this._yMin = _yMin;
        this._yMax = _yMax;
        this._width = _width;
        this._height = _height;
    }
    public  Double _xMin()
    {
        return  _xMin;
    }
    public  Double _xMax()
    {
        return  _xMax;
    }
    public  Double _yMin()
    {
        return  _yMin;
    }
    public  Double _yMax()
    {
        return  _yMax;
    }
    public  Integer _width()
    {
        return  _width;
    }
    public  Integer _height()
    {
        return  _height;
    }
    @Override
    public String toString()
    {
        return "PlaneSave{"+"_xMin='"+ _xMin + '\''
                + ", _xMax='"+_xMax + '\''
                + ",_yMin='"+_yMin + '\''
                +",_yMax='"+_yMax +'\''
                +  ",_width='"+_width +'\''
                +  ",_height='"+-_height +'\''
                +'}';
    }

}
