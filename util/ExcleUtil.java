package crawl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcleUtil {

	/**
	 * 读取数据
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<List<Object>> importExcel(File file) throws IOException {
		String fileName = file.getName();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}
	
	/**
	 * 读取 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static List<List<Object>> read2003Excel(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
															// 字符
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0");// 格式化数字
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					// System.out.println(i+"行"+j+" 列 is String type");
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// System.out.println(i+"行"+j+" 列 is Number type ;
					// DateFormt:"+cell.getCellStyle().getDataFormatString());
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					// System.out.println(i+"行"+j+" 列 is Boolean type");
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					// System.out.println(i+"行"+j+" 列 is Blank type");
					value = "";
					break;
				default:
					// System.out.println(i+"行"+j+" 列 is default type");
					value = cell.toString();
				}
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}

	/**
	 * 读取Office 2007 excel
	 */
	private static List<List<Object>> read2007Excel(File file) throws IOException {
		List<List<Object>> list = new LinkedList<List<Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			List<Object> linked = new LinkedList<Object>();
			for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				DecimalFormat df = new DecimalFormat("0");// 格式化 number String
															// 字符
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
				DecimalFormat nf = new DecimalFormat("0");// 格式化数字
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					// System.out.println(i+"行"+j+" 列 is String type");
					value = cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// System.out.println(i+"行"+j+" 列 is Number type ;
					// DateFormt:"+cell.getCellStyle().getDataFormatString());
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = nf.format(cell.getNumericCellValue());
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					// System.out.println(i+"行"+j+" 列 is Boolean type");
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					// System.out.println(i+"行"+j+" 列 is Blank type");
					value = "";
					break;
				default:
					// System.out.println(i+"行"+j+" 列 is default type");
					value = cell.toString();
				}
				if (value == null || "".equals(value)) {
					continue;
				}
				linked.add(value);
			}
			list.add(linked);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param list   需要导出到excle的数据
	 * @param path   excel导出的路径
	 * @param fileName   导出的excel的文件名
	 * @param FileType   导出的excel的版本  xls或者xlsx
	 * @param labels     导出的excel的表头数组
	 * @return
	 */
	public static String exportExcel(List<List<String>> list, String path,String fileName,String FileType,String[] labels) {
		String re = "";
		//创建工作文档
		Workbook wb = null;
		if ("xls".equals(FileType)) {
			wb = new HSSFWorkbook();
		} else if ("xlsx".equals(FileType)) {
			wb = new XSSFWorkbook();
		}
		
		//创建sheet页
		Sheet sheet = null;//wb.createSheet("sheet1");
//		Row labelRow = sheet.createRow(0);
//		for (int i = 0; i < labels.length; i++) {
//			Cell cell = labelRow.createCell(i);
//			cell.setCellValue(labels[i]);
//		}
		if(null != list && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				//每60000万条数据创建一个sheet
				if((i%60000)==0){
					sheet = wb.createSheet("sheet"+(i%60000));
					Row labelRow = sheet.createRow(0);
					for (int k = 0; k < labels.length; k++) {
						Cell cell = labelRow.createCell(k);
						cell.setCellValue(labels[k]);
					}
				}
				
				List<String> rowList = list.get(i);
				//每一個sheet从第一行开始
				Row row = sheet.createRow((i%60000)+1);
				for (int j = 0; j < rowList.size(); j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(rowList.get(j));
				}
				
				//每一个sheet页的最后一行数据写完  设置单元格宽度自适应
				if((i+1)%60000 == 0 || (i+1)== list.size()){
				       if(null != sheet){
							for (int j = 0; j < labels.length; j++) {
								 sheet.autoSizeColumn(j);
							}
				       }	
				}
			}
		}else{
			sheet = wb.createSheet("sheet");
			Row labelRow = sheet.createRow(0);
			for (int k = 0; k < labels.length; k++) {
				Cell cell = labelRow.createCell(k);
				cell.setCellValue(labels[k]);
			}
		}
		

		OutputStream stream;
		try {
			re = path+fileName+"."+FileType;
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			stream = new FileOutputStream(path+fileName+"."+FileType);
	        //写入数据  
	        wb.write(stream);  
	        //关闭文件流  
	        stream.close();  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  

		return re;
	}
}
