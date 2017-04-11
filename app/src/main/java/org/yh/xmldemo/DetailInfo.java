package org.yh.xmldemo;

/**
 * Created by yhlyl on 2017/3/22.
 * 测量信息数据类
 */

public class DetailInfo
{
    private String measureType;//测量类型
    private String measureValue;//测量数据
    private String measureDateTime;//测量时间
    private String recorderName;//护士名称
    private String recorderID;//护士ID

    public String getMeasureType()
    {
        return measureType;
    }

    public void setMeasureType(String measureType)
    {
        this.measureType = measureType;
    }

    public String getMeasureValue()
    {
        return measureValue;
    }

    public void setMeasureValue(String measureValue)
    {
        this.measureValue = measureValue;
    }

    public String getMeasureDateTime()
    {
        return measureDateTime;
    }

    public void setMeasureDateTime(String measureDateTime)
    {
        this.measureDateTime = measureDateTime;
    }

    public String getRecorderName()
    {
        return recorderName;
    }

    public void setRecorderName(String recorderName)
    {
        this.recorderName = recorderName;
    }

    public String getRecorderID()
    {
        return recorderID;
    }

    public void setRecorderID(String recorderID)
    {
        this.recorderID = recorderID;
    }

    @Override
    public String toString()
    {
        return "DetailInfo{" +
                "measureType='" + measureType + '\'' +
                ", measureValue='" + measureValue + '\'' +
                ", measureDateTime='" + measureDateTime + '\'' +
                ", recorderName='" + recorderName + '\'' +
                ", recorderID='" + recorderID + '\'' +
                '}';
    }
}
