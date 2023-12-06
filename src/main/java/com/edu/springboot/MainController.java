package com.edu.springboot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.springboot.mybatis.CommonDTO;
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
	//엑셀파일 업로드 및 등록
	@RequestMapping("/admin34/print01.do")
	public String sbadmin_print01(Model model) {
		model.addAttribute("rows", dao.select());
		return "sbadmin/print01";
	}	
	//DB에 등록된 엑셀 내용을 리스트로 출력
	@RequestMapping("/admin34/print02.do")
	public String sbadmin_print02(Model model) {		
		model.addAttribute("rows", dao.selectExcel());
		return "sbadmin/print02";
	}
	//프린트 팝업창
	@RequestMapping("/admin34/onlyPrint.do")
	public ModelAndView sbadmin_onlyPrint(CommonDTO commonDTO, ModelAndView mv) {
		/*
		발주처 : 상호 
		지종 : 지종
		평량 : 규격 - 첫번째
		규격 : 규격 - 두번째 
		수량 : ??
		품명 : ??
		입고처 : 도착지
		출고일 : 날짜 
		*/
		commonDTO = dao.selectOne(commonDTO);		
		mv.addObject("row", commonDTO);
		
		Map<String, String> otherInfo = new HashMap<>();
		//평량, 규격
		String[] c04Arr = commonDTO.getCol04().split(" ");
		otherInfo.put("pyeong", c04Arr[0]);
		otherInfo.put("gyugyeog", c04Arr[1]);
				
		
		mv.addObject("otherInfo", otherInfo);
		mv.setViewName("sbadmin/onlyPrint");
		return mv;
	}	
	@PostMapping("/admin34/uploadProcess.do")
	public String uploadProcess(HttpServletRequest req, Model model, CommonDTO commonDTO) {		
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
		    
		    
		    //엑셀읽기
/**
롤재단 => getSheetAt(4)
날짜 번호 상호 지종 규격 비고 톤수 내용 도착지 실출고 번호 1호 2호 자차 용차 용차번호  
0    1    2    3    4    5    6     7   8       9     10   11  12  13   14   15   
-------------------------------------------------------------------------------------------
길로틴 => getSheetAt(5)
날짜 번호 상호 지종 규격 연수(R) 내용 도착지 번호 자차 용차 용차번호 기사님
0    1    2	   3    4    5       6     7     8     9   10    11       12
*/
		    StringBuffer sb = new StringBuffer();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar calendar = Calendar.getInstance();
		    String strToday = sdf.format(calendar.getTime());
		    Workbook workbook = new XSSFWorkbook(uploadDir+ File.separator +savedFileName);
		    Sheet worksSheet = workbook.getSheetAt(4);
		    for(int i=1 ; i<worksSheet.getPhysicalNumberOfRows() ; i++) {
		    	//레코드
		    	Row row = worksSheet.getRow(i);
		    	//데이터정리
		    	String col00 = row.getCell(0).getStringCellValue();
		    	String col01 = row.getCell(1).getStringCellValue();
		    	String col02 = row.getCell(2).getStringCellValue();
		    	String col03 = row.getCell(3).getStringCellValue();
		    	String col04 = row.getCell(4).getStringCellValue();
		    	String col05 = row.getCell(5).getStringCellValue();
		    	String col06 = Double.toString(row.getCell(6).getNumericCellValue());		    	
		    	String col07 = row.getCell(7).getStringCellValue();
		    	String col08 = row.getCell(8).getStringCellValue();
		    	String col09 = Double.toString(row.getCell(9).getNumericCellValue());
		    	String col10 = row.getCell(10).getStringCellValue();
		    	String col11 = Boolean.toString(row.getCell(11).getBooleanCellValue());
		    	String col12 = Boolean.toString(row.getCell(12).getBooleanCellValue());
		    	String col13 = Boolean.toString(row.getCell(13).getBooleanCellValue());
		    	String col14 = Boolean.toString(row.getCell(14).getBooleanCellValue());
		    	//String col15 = Double.toString(row.getCell(15).getNumericCellValue());
		    	
		    	//출력용 문자열 만들기
//		    	sb.append(col00+"="+col01+"="+col02+"="+col03+"="+col04+"="+col05+"="
//		    			+col06+"="+col07+"="+col08+"="+col09+"="+col10+"="+col11+"="
//		    			+col12+"="+col13+"="+col14+"="+col15+"<br>");
		    	
		    	//DB저장을 위해 DTO에 저장
		    	commonDTO.setCol00(col00);
		    	commonDTO.setCol01(col01);
		    	commonDTO.setCol02(col02);
		    	commonDTO.setCol03(col03);
		    	commonDTO.setCol04(col04);
		    	commonDTO.setCol05(col05);
		    	commonDTO.setCol06(col06);
		    	commonDTO.setCol07(col07);
		    	commonDTO.setCol08(col08);
		    	commonDTO.setCol09(col09);
		    	commonDTO.setCol10(col10);
		    	commonDTO.setCol11(col11);
		    	commonDTO.setCol12(col12);
		    	commonDTO.setCol13(col13);
		    	commonDTO.setCol14(col14);
		    	//commonDTO.setCol15(col15);
		    	commonDTO.setFlag(strToday);
		    	
		    	//insert 
		    	dao.insert(commonDTO);
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
