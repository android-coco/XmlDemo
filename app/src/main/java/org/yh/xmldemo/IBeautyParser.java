package org.yh.xmldemo;

import java.io.InputStream;

public interface IBeautyParser {
	/**
	 *
	 * 解析输入流，获取Beauty列表
	 * @param is
	 * @return
	 * @throws Exception
	 */
	 VitalSignsInfo parse(InputStream is) throws Exception;

	/**
	 *
	 * 序列化VitalSignsInfo对象集合，得到XML形式的字符串
	 * @param vitalSignsInfo
	 * @return
	 * @throws Exception
	 */
	 String serialize(VitalSignsInfo vitalSignsInfo) throws Exception;


}
