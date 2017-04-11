<?php
$xml_string = $_POST['strVitalSignsInfo'];
$xml_string = trim($xml_string);
//转换为simplexml对象
$xml_object = simplexml_load_string($xml_string);
if (empty($xml_object)) 
{
	$result = array('result' => '1' );
	echo json_encode($result,JSON_UNESCAPED_UNICODE);
}else
{

	var_dump(xml_object);
	//foreach循环遍历
	foreach($xml_object->children() as $childItem) 
	{  
	 	//输出xml节点名称和值    
		//echo $childItem->getName() . "--".$childItem;
	 	//其他操作省略
	 	$name = $childItem -> getName();
	 	if ($name == 'PatientID') 
	 	{
	 		$PatientID = $childItem;
	 		echo "PatientID:".$childItem;
	 	}else if ($name == 'VisitID') 
	 	{
	 		$VisitID = $childItem;
	 		echo "VisitID:".$childItem;
	 	}else if ($name == 'MeasureType') 
	 	{
	 		echo "MeasureType:".$childItem;
	 	}
	}
}

// //接收传送的数据
// $xml_string = file_get_contents("php://input");
// $xml_string = trim($xml_string);
// //转换为simplexml对象
// $xml_object = simplexml_load_string($xml_string);
// if (empty($xml_object)) 
// {
// 	$result = array('result' => '1' );
// 	echo json_encode($result,JSON_UNESCAPED_UNICODE);
// }else
// {
// 	$xml_arr = get_object_vars($xml_object);
// 	//foreach循环遍历
// 	foreach($xml_arr->children() as $childItem) 
// 	{  
// 	 	//输出xml节点名称和值    
// 		echo $childItem->getName() . "->".$childItem."<br />";   
// 	 	//其他操作省略
// 	}
// }
?>
