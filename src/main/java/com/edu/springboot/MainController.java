package com.edu.springboot;

import java.io.File;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.mybatis.MyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import utils.MyFunctions;

@Controller
public class MainController {
	
	@Autowired
	MyService dao;
	
	@RequestMapping("/")
	public String home() {		
		return "main";
	} 
	@RequestMapping("/admin34/index.do")
	public String sbadmin_index() {		
		return "sbadmin/index";
	}
	@RequestMapping("/admin34/login.do")
	public String sbadmin_login() {		
		return "sbadmin/login";
	}	
	@RequestMapping("/admin34/print01.do")
	public String sbadmin_print01(Model model) {
		model.addAttribute("rows", dao.select());
		return "sbadmin/print01";
	}	
	@RequestMapping("/admin34/onlyPrint.do")
	public String sbadmin_onlyPrint() {		
		return "sbadmin/onlyPrint";
	}	
	@PostMapping("/admin34/uploadProcess.do")
	public String uploadProcess(HttpServletRequest req, Model model) {		
		try {
			//파일업로드
			String uploadDir = ResourceUtils
				.getFile("classpath:static/uploads/").toPath().toString();
			System.out.println("물리적경로=>"+uploadDir);
			Part part = req.getPart("ofile");	
			String partHeader = part.getHeader("content-disposition");
		    System.out.println("partHeader="+ partHeader);
		    String[] phArr = partHeader.split("filename=");
		    String originalFileName = phArr[1].trim().replace("\"", "");
		    if (!originalFileName.isEmpty()) {				
				part.write(uploadDir+ File.separator +originalFileName);
			}
		    
		    String savedFileName = 
		    	MyFunctions.renameFile(uploadDir, originalFileName);
		    
		    model.addAttribute("originalFileName", originalFileName);			
		    model.addAttribute("savedFileName", savedFileName);
		    
		    StringBuffer sb = new StringBuffer();
		    
		    //엑셀읽기
/**
롤재단 => getSheetAt(4)
날짜 번호 상호 지종 규격 비고 톤수 내용 도착지 실출고 번호 1호 2호 자차 용차 용차번호  
0   1	2	3	4	5  6   7  8	    9	10 11 12  13  14  15   
-------------------------------------------------------------------------------------------
길로틴 => getSheetAt(5)
날짜 번호 상호 지종 규격 연수(R) 내용 도착지 번호 자차 용차 용차번호 기사님
0   1	2	3	4	5	  6	  7	   8  9  10  11    12
*/
		    Workbook workbook = new XSSFWorkbook(uploadDir+ File.separator +savedFileName);
		    Sheet worksSheet = workbook.getSheetAt(4);
		    for(int i=1 ; i<worksSheet.getPhysicalNumberOfRows() ; i++) {
		    	Row row = worksSheet.getRow(i);
		    	sb.append(row.getCell(0).getStringCellValue()+"="
		    			+row.getCell(1).getStringCellValue()+"="
		    			+row.getCell(2).getStringCellValue()+"="
		    			+row.getCell(3).getStringCellValue()+"="
		    			+row.getCell(4).getStringCellValue()+"="
		    			+row.getCell(5).getStringCellValue()+"="
		    			+row.getCell(6).getNumericCellValue()+"="
		    			+row.getCell(7).getStringCellValue()+"="
		    			+row.getCell(8).getStringCellValue()+"="
		    			+row.getCell(9).getNumericCellValue()+"="
		    			+row.getCell(10).getStringCellValue()+"<br>");
//		    			+row.getCell(11).getBooleanCellValue()+"="
//		    			+row.getCell(12).getBooleanCellValue()+"="
//		    			+row.getCell(13).getBooleanCellValue()+"="
//		    			+row.getCell(14).getBooleanCellValue()+"<br>");		    	
		    }
		    
		    model.addAttribute("sb", sb);
		    workbook.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("업로드 실패 or Excel파일 읽기 실패");
		}
		
		return "sbadmin/print01";
	}	
 
	 




}
