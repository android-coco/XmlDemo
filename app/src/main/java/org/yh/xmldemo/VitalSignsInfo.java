package org.yh.xmldemo;

import java.util.ArrayList;

/**
 * Created by yhlyl on 2017/3/22.
 */

public class VitalSignsInfo
{
    private String patientID;//患者ID
    private String visitID;//访问ID
    private ArrayList<DetailInfo> detailInfos;//测量数据

    public String getPatientID()
    {
        return patientID;
    }

    public void setPatientID(String patientID)
    {
        this.patientID = patientID;
    }

    public String getVsitID()
    {
        return visitID;
    }

    public void setVsitID(String visitID)
    {
        this.visitID = visitID;
    }

    public ArrayList<DetailInfo> getDetailInfos()
    {
        return detailInfos;
    }

    public void setDetailInfos(ArrayList<DetailInfo> detailInfos)
    {
        this.detailInfos = detailInfos;
    }

    @Override
    public String toString()
    {
        return "VitalSignsInfo{" +
                "patientID='" + patientID + '\'' +
                ", visitID='" + visitID + '\'' +
                ", detailInfos=" + detailInfos +
                '}';
    }
}
