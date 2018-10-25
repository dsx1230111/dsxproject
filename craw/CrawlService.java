package crawl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import crawl.model.Company;
import crawl.util.CrawlUtil;
import crawl.util.ExcleUtil;

/**
 * 爬取企业数据
 * 企业数据来源：天眼查
 * @author shaoxiao.dong
 *
 */
public class CrawlService {

	
	/**
	 * 读取excle文档企业名称
	 * @return
	 */
	public static List<String> getCompanyName(){
		List<String> companyNameList = new ArrayList<String>();
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		File file = new File("E:\\副本.xlsx");
		try {
			dataList = ExcleUtil.importExcel(file);
			for (int i = 1; i < dataList.size(); i++) {
				String companyName = (String) dataList.get(i).get(0);
				companyNameList.add(companyName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return companyNameList;
	} 
	
	
	/**
	 * 爬取数据
	 * @param args
	 */
	public static List<List<String>> crawl(List<String> companyNameList){
		List<List<String>> dataList = new ArrayList<List<String>>();
		String companyName = "";
		for (int i = 0; i < companyNameList.size(); i++) {
			companyName = companyNameList.get(i);
			List<String> list = CrawlUtil.getData(companyName);
			dataList.add(list);
		}
		return dataList;
	}
	
	/**
	 * 生成excle
	 * @param args
	 */
	public static void exportData(List<List<String>> DataList){
		String path = "E:\\";
		String fileName = "company";
		String FileType = "xlsx";
		ExcleUtil.exportExcel(DataList, path, fileName, FileType, null);
	}
	
	
	public static void main(String[] args) {
		List<String> companyNameList = getCompanyName();
		System.out.println(companyNameList);
		List<List<String>> dataList = crawl(companyNameList);
		System.out.println(dataList);
		exportData(dataList);
	}
	
}
