<?php

//接收传送的数据
$fileContent = file_get_contents("php://input"); 

//转换为simplexml对象
$xmlResult = simplexml_load_string($fileContent);
var_dump(xmlResult);
//foreach循环遍历
foreach($xmlResult->children() as $childItem)    //遍历所有节点数据
{
	//输出xml节点名称和值
	echo $childItem->getName() . "->".$childItem."<br />"; 

	//其他操作省略
}

?>