package org.yh.xmldemo;

import android.util.Xml;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class BeautyParserImpl implements IBeautyParser
{

    @Override
    public VitalSignsInfo parse(InputStream is) throws Exception
    {
        VitalSignsInfo info = null;
        ArrayList<DetailInfo> list = null;
        DetailInfo beauty = null;

        // 由android.util.Xml创建一个XmlPullParser实例
        XmlPullParser xpp = Xml.newPullParser();
        // 设置输入流 并指明编码方式
        xpp.setInput(is, "UTF-8");
        // 产生第一个事件
        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            switch (eventType)
            {
                // 判断当前事件是否为文档开始事件
                case XmlPullParser.START_DOCUMENT:
                    info = new VitalSignsInfo(); // 初始化对象
                    list = new ArrayList<>();
                    break;
                // 判断当前事件是否为标签元素开始事件
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("Detail"))
                    { // 判断开始标签元素是否是Detail
                        beauty = new DetailInfo();
                    }
                    else if (xpp.getName().equals("PatientID"))
                    {
                        // 判断开始标签元素是否是PatientID
                        eventType = xpp.next();//让解析器指向PatientID属性的值
                        // 得到PatientID标签的属性值，并设置info的PatientID
                        info.setPatientID(xpp.getText());
                    }
                    else if (xpp.getName().equals("VisitID"))
                    {
                        // 判断开始标签元素是否是VisitID
                        eventType = xpp.next();//让解析器指向VisitID属性的值
                        // 得到VisitID标签的属性值，并设置info的VisitID
                        info.setVsitID(xpp.getText());
                    }
                    else if (xpp.getName().equals("MeasureType"))
                    {
                        // 判断开始标签元素是否是MeasureType
                        eventType = xpp.next();//让解析器指向MeasureType属性的值
                        // 得到MeasureType标签的属性值，并设置beauty的MeasureType
                        beauty.setMeasureType(xpp.getText());
                    }
                    else if (xpp.getName().equals("MeasureValue"))
                    { // 判断开始标签元素是否是MeasureValue
                        eventType = xpp.next();//让解析器指向MeasureValue属性的值
                        // 得到MeasureValue标签的属性值，并设置beauty的MeasureValue
                        beauty.setMeasureValue(xpp.getText());
                    }else if (xpp.getName().equals("MeasureDateTime"))
                    { // 判断开始标签元素是否是MeasureDateTime
                        eventType = xpp.next();//让解析器指向MeasureDateTime属性的值
                        // 得到MeasureDateTime标签的属性值，并设置beauty的MeasureDateTime
                        beauty.setMeasureDateTime(xpp.getText());
                    }else if (xpp.getName().equals("Recorder"))
                    { // 判断开始标签元素是否是Recorder
                        eventType = xpp.next();//让解析器指向Recorder属性的值
                        // 得到Recorder标签的属性值，并设置beauty的RecorderName
                        beauty.setRecorderName(xpp.getText());
                    }else if (xpp.getName().equals("RecorderID"))
                    { // 判断开始标签元素是否是RecorderID
                        eventType = xpp.next();//让解析器指向RecorderID属性的值
                        // 得到RecorderID标签的属性值，并设置beauty的RecorderID
                        beauty.setRecorderID(xpp.getText());
                    }
                    break;

                // 判断当前事件是否为标签元素结束事件
                case XmlPullParser.END_TAG:
                    if (xpp.getName().equals("Detail"))
                    { // 判断结束标签元素是否是book
                        list.add(beauty); // 将book添加到books集合
                        info.setDetailInfos(list);
                        beauty = null;
                    }
                    break;
            }
            // 进入下一个元素并触发相应事件
            eventType = xpp.next();
        }
        return info;

    }


    @Override
    public String serialize(VitalSignsInfo vitalSignsInfo) throws Exception
    {
        String xmlWriter = null;
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element eleRoot = doc.createElement("VitalSigns");//第一层
            //属性
            //eleRoot.setAttribute("author", "homer");
            //eleRoot.setAttribute("date", "2012-04-26");
            doc.appendChild(eleRoot);

            Element patientID = doc.createElement("PatientID");
            Node patientID_number = doc.createTextNode(vitalSignsInfo.getPatientID() + "");
            patientID.appendChild(patientID_number);
            eleRoot.appendChild(patientID);

            Element visitID = doc.createElement("VisitID");
            Node visitID_number = doc.createTextNode(vitalSignsInfo.getVsitID() + "");
            visitID.appendChild(visitID_number);
            eleRoot.appendChild(visitID);

            Element eleRoot1 = doc.createElement("VitalSigns");//数据层
            eleRoot.appendChild(eleRoot1);



            ArrayList<DetailInfo> detailInfolist = vitalSignsInfo.getDetailInfos();

            int personsLen = detailInfolist.size();
            for (int i = 0; i < personsLen; i++)
            {
                Element elePerson = doc.createElement("Detail");
                eleRoot1.appendChild(elePerson);
                //MeasureType
                Element eleMeasureType = doc.createElement("MeasureType");
                Node nodeMeasureType = doc.createTextNode(detailInfolist.get(i).getMeasureType() + "");
                eleMeasureType.appendChild(nodeMeasureType);
                elePerson.appendChild(eleMeasureType);

                //MeasureValue
                Element eleMeasureValue = doc.createElement("MeasureValue");
                Node nodeMeasureValue = doc.createTextNode(detailInfolist.get(i).getMeasureValue() + "");
                eleMeasureValue.appendChild(nodeMeasureValue);
                elePerson.appendChild(eleMeasureValue);


                //MeasureDateTime
                Element eleMeasureDateTime = doc.createElement("MeasureDateTime");
                Node nodeMeasureDateTime = doc.createTextNode(detailInfolist.get(i).getMeasureDateTime() + "");
                eleMeasureDateTime.appendChild(nodeMeasureDateTime);
                elePerson.appendChild(eleMeasureDateTime);

                //RecorderName
                Element eleRecorderName= doc.createElement("Recorder");
                Node nodeRecorderName = doc.createTextNode(detailInfolist.get(i).getRecorderName() + "");
                eleRecorderName.appendChild(nodeRecorderName);
                elePerson.appendChild(eleRecorderName);

                //nodeRecorderID
                Element eleRecorderID = doc.createElement("RecorderID");
                Node nodeRecorderID = doc.createTextNode(detailInfolist.get(i).getRecorderID() + "");
                eleRecorderID.appendChild(nodeRecorderID);
                elePerson.appendChild(eleRecorderID);
            }


            Properties properties = new Properties();
            properties.setProperty(OutputKeys.INDENT, "yes");
            properties.setProperty(OutputKeys.MEDIA_TYPE, "xml");
            properties.setProperty(OutputKeys.VERSION, "1.0");
            properties.setProperty(OutputKeys.ENCODING, "utf-8");
            properties.setProperty(OutputKeys.METHOD, "xml");
            properties.setProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperties(properties);

            DOMSource domSource = new DOMSource(doc.getDocumentElement());
            OutputStream output = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(output);
            transformer.transform(domSource, result);

            xmlWriter = output.toString();

        }
        catch (ParserConfigurationException e)
        {      // factory.newDocumentBuilder
            e.printStackTrace();
        }
        catch (DOMException e)
        {                      // doc.createElement
            e.printStackTrace();
        }
        catch (TransformerFactoryConfigurationError e)
        {      // TransformerFactory.newInstance
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e)
        {     // transformerFactory.newTransformer
            e.printStackTrace();
        }
        catch (TransformerException e)
        {              // transformer.transform
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return xmlWriter.toString();
    }

}
