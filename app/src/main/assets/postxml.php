<?php
//首先检测是否支持curl
if (!extension_loaded("curl")) {
	trigger_error("对不起，请开启curl功能模块！", E_USER_ERROR);
}

//构造xml
$xmldata="<VitalSigns><PatientID>000091381000</PatientID><VisitID>1</VisitID><VitalSigns><Detail><MeasureType>TW</MeasureType><MeasureValue>36</MeasureValue><MeasureDateTime>2013-03-06 2:35:00</MeasureDateTime><Recorder>护士1</Recorder><RecorderID>00375</RecorderID></Detail><Detail><MeasureType>TW</MeasureType><MeasureValue>37.1</MeasureValue><MeasureDateTime>2013-03-06 7:01:00</MeasureDateTime><Recorder>护士1</Recorder><RecorderID>00375</RecorderID></Detail>
    </VitalSigns>
</VitalSigns>";

//初始一个curl会话
$curl = curl_init();

//设置url
curl_setopt($curl, CURLOPT_URL,"http://localhost:8080/dealxml.php");

//设置发送方式：post
curl_setopt($curl, CURLOPT_POST, true);

//设置发送数据
curl_setopt($curl, CURLOPT_POSTFIELDS, $xmldata);

//抓取URL并把它传递给浏览器
curl_exec($curl);

//关闭cURL资源，并且释放系统资源
curl_close($curl);
?>
