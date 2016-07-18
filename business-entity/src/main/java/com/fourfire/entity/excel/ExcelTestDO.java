package com.fourfire.entity.excel;

import com.fourfire.entity.annotation.Column;
import com.fourfire.entity.annotation.ExcelObject;

/**
 * Excel操作的测试类
 * 
 * @author liuyi
 * @date 2016-07-15
 */
@ExcelObject
public class ExcelTestDO {
	@Column(name="姓名")
	public String name;
	@Column(name="性别")
	public boolean gender;
	@Column(name="年龄")
	public int age;

	@Override
	public String toString() {
		return "ExcelTestDO [name=" + name + ", gender=" + gender + ", age="
				+ age + "]";
	}
	
}
