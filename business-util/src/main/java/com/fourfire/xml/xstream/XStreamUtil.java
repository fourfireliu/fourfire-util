package com.fourfire.xml.xstream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import com.fourfire.xml.xstream.factory.XStreamFactory;
import com.thoughtworks.xstream.XStream;

public class XStreamUtil {
	private static final Logger logger = LogManager.getLogger("XStreamUtil");
	
	/**
	 * 用XStream将XML转换为Object
	 */
	public static Object transferFromXml2Obj(String xmlString, 
			@SuppressWarnings("rawtypes") Class targetClass) {
		if (StringUtils.isEmpty(xmlString) || targetClass == null) {
			return null;
		}

		XStream xStream = XStreamFactory.newXStream();
		xStream.autodetectAnnotations(true);
		xStream.alias("xml", targetClass);

		Object target = null;
		try {
			target = targetClass.cast(xStream.fromXML(xmlString));
		} catch (Exception e) {
			logger.error("unknown error", e);
		}
		
		return target;
	}
	
	/**
	 * 通过XStream将Object转换为XML
	 */
	public static String transferFromObj2Xml(Object o) {
		if (o == null) {
			return null;
		}
		
		XStream xStream = XStreamFactory.newXStream();
		xStream.autodetectAnnotations(true);
		
		return xStream.toXML(o);
	}
}
