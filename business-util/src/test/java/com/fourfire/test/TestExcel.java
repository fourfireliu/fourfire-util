package com.fourfire.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.Assert;

import com.fourfire.entity.excel.ExcelTestDO;
import com.fourfire.excel.ExcelUtil;

public class TestExcel {
	
	@Test
	public void testCreateExcel() {
		ExcelTestDO excelTestDO = new ExcelTestDO();
		excelTestDO.age = 11;
		excelTestDO.gender = true;
		excelTestDO.name = "liuyi";
		
		ExcelTestDO excelTestDO1 = new ExcelTestDO();
		excelTestDO1.age = 30;
		excelTestDO1.gender = true;
		excelTestDO1.name = "fourfire";
		
		List<Object> excelTestDOs = new ArrayList<Object>();
		excelTestDOs.add(excelTestDO);
		excelTestDOs.add(excelTestDO1);
		
		Assert.isTrue(ExcelUtil.createExcel(excelTestDOs, "test.xls"));
	}
}
