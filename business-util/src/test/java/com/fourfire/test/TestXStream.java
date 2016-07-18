package com.fourfire.test;

import org.junit.Test;

import com.fourfire.entity.enums.MsgType;
import com.fourfire.entity.xml.InputMessage;
import com.fourfire.xml.xstream.XStreamUtil;



public class TestXStream {
	@Test
	public void testToXml() {
		InputMessage response = new InputMessage();
		response.setToUserName("fourfire");
		response.setFromUserName("liuyi");
		response.setCreateTime(System.currentTimeMillis());
		response.setMsgType(MsgType.TEXT);
		response.setContent(", 是这个意思吗?");
		
		System.out.println(XStreamUtil.transferFromObj2Xml(response));
	}
}
