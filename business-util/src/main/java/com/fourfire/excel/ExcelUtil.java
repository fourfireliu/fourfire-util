package com.fourfire.excel;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fourfire.entity.annotation.Column;
import com.fourfire.entity.annotation.ExcelObject;

/**
 * 调用apache-poi框架对excel文件进行操作
 * 
 * @author liuyi
 * @date 2016-07-15
 */
public class ExcelUtil {
	private static final Logger logger = LogManager.getLogger("ExcelUtil");
	
	/**
	 * 根据传入对象列表创建Excel表格(xls格式)
	 */
	public static boolean createExcel(List<Object> excelRowDOs, String filePath) {
		if (CollectionUtils.isEmpty(excelRowDOs) || StringUtils.isEmpty(filePath)) {
			return false;
		}
		
		Optional<Object> optional = excelRowDOs.stream().filter(
				excelTestDO -> excelTestDO != null).findFirst();
		if (optional.isPresent()) {
			List<Field> columnFields = getExcelColumnFields(optional.get().getClass());
			if (CollectionUtils.isEmpty(columnFields)) {
				return false;
			}
			
			try (FileOutputStream fileOut = new FileOutputStream(filePath);
					HSSFWorkbook wb = new HSSFWorkbook()) {
				HSSFSheet sheet = wb.createSheet();
				
				//生成Excel表头
				HSSFRow nameRow = sheet.createRow(0);
				int count = 0;
				for (Field field : columnFields) {
					nameRow.createCell(count).setCellValue(field.getAnnotation(Column.class).name());
					count++;
				}
				
				//生成Excel表内容
				int rowCount = 1;
				for (Object object : excelRowDOs) {
					if (object != null && 
							optional.get().getClass().equals(object.getClass())) {
						HSSFRow valueRow = sheet.createRow(rowCount);
						rowCount++;
						
						count = 0;
						for (Field field : columnFields) {
							Object value = null;
							try {
								value = field.get(object);
							} catch (Exception e) {
								logger.error("error in get value", e);
								value = "";
							} finally {
								valueRow.createCell(count).setCellValue(
										value != null ? value.toString() : "");
								count++;
							}
						}
					}
				}
				wb.write(fileOut);

				return true;
			} catch (Exception e) {
				logger.error("unknow error", e);
			}
		}
		
		return false;

	}
	
	/**
	 * 获取Class里带有Column注解的变量
	 */
	private static List<Field> getExcelColumnFields(@SuppressWarnings("rawtypes") Class objClass) {
		List<Field> filterFields = new ArrayList<Field>();
		
		if (isExcelObjectClass(objClass)) {
			Field[] fields = objClass.getDeclaredFields();
			if (fields != null) {
				for (Field field : fields) {
					Column column = field.getAnnotation(Column.class);
					if (column != null && !StringUtils.isEmpty(column.name())) {
						filterFields.add(0, field);
					}
				}
			}
		}
		
		return filterFields;
	}
	
	/**
	 * 判断类是否有标识Excel的注解
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean isExcelObjectClass(Class objClass) {
		return objClass.getAnnotation(ExcelObject.class) != null;
	}
}
