package com.edu.springboot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
	//대쉬보드
	@RequestMapping("/admin34/index.do")
	public String sbadmin_index() {		
		return "sbadmin/index";
	}
	//로그인
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
	//파일업로드 및 DB등록
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
		    //StringBuffer sb = new StringBuffer();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar calendar = Calendar.getInstance();
		    String strToday = sdf.format(calendar.getTime());
		    Workbook workbook = new XSSFWorkbook(uploadDir+ File.separator +savedFileName);
		    
/**
롤재단 => getSheetAt(4)
날짜 번호 상호 지종 규격 비고 톤수 사이즈및수량 제지 실출고 품명 도착지 번호 /까지/ 1호 2호 자차 용차 용차번호												
0    1    2    3    4    5    6     7           8     9     10   11     12   /까지/ 13  14  15
*/		    
		    //롤제단
		    Sheet worksSheet = workbook.getSheetAt(4);
		    for(int i=1 ; i<worksSheet.getPhysicalNumberOfRows() ; i++) {
		    	//레코드
		    	Row row = worksSheet.getRow(i);
		    	//데이터정리
		    	String col00 = row.getCell(0).getStringCellValue();//날짜
		    	String col01 = row.getCell(1).getStringCellValue();//번호
		    	String col02 = row.getCell(2).getStringCellValue();//상호
		    	String col03 = row.getCell(3).getStringCellValue();//지종
		    	String col04 = row.getCell(4).getStringCellValue();//규격
		    	String col05 = row.getCell(5).getStringCellValue();//비고
		    	String col06 = Double.toString(row.getCell(6).getNumericCellValue());//톤수(실수)		    	
		    	String col07 = row.getCell(7).getStringCellValue();
		    	String col08 = row.getCell(8).getStringCellValue();
		    	
		    	String col09;//실출고(숫자 or 텍스트)
		    	//System.out.println(col00+" 타입=>"+row.getCell(9).getCellType());
		    	if(row.getCell(9).getCellType()==CellType.NUMERIC) {
		    		col09 = Double.toString(row.getCell(9).getNumericCellValue());
		    	}
		    	else {
		    		col09 = row.getCell(9).getStringCellValue();
		    	}
		    	
		    	String col10 = row.getCell(10).getStringCellValue();
		    	String col11 = row.getCell(11).getStringCellValue();
		    	String col12 = row.getCell(12).getStringCellValue();//번호
//		    	String col13 = Boolean.toString(row.getCell(13).getBooleanCellValue());
//		    	String col14 = Boolean.toString(row.getCell(14).getBooleanCellValue());
//		    	String col15 = Double.toString(row.getCell(15).getNumericCellValue());
		    	
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
//		    	commonDTO.setCol13(col13);
//		    	commonDTO.setCol14(col14);
		    	commonDTO.setFlag(strToday);
		    	commonDTO.setGubun("roll");
		    	
		    	//insert
		    	if(!col01.equals("") && !col02.equals(""))
		    		dao.insert(commonDTO);
		    }

/**
길로틴 => getSheetAt(5)
날짜 번호 상호 지종 규격 연수(R) 재단사이즈 제지사 품명 도착지 /까지/ 번호 자차 용차 용차번호 기사님												
0    1    2    3    4    5       6          7      8    9      /까지/ 10   11    12
 */
		    //길로틴
		    Sheet worksSheet2 = workbook.getSheetAt(5);
		    for(int i=1 ; i<worksSheet2.getPhysicalNumberOfRows() ; i++) {
		    	//레코드
		    	Row row = worksSheet2.getRow(i);
		    	//데이터정리
		    	String col00 = row.getCell(0).getStringCellValue();
		    	String col01 = row.getCell(1).getStringCellValue();
		    	String col02 = row.getCell(2).getStringCellValue();
		    	String col03 = row.getCell(3).getStringCellValue();
		    	String col04 = row.getCell(4).getStringCellValue();
		    	String col05 = Double.toString(row.getCell(5).getNumericCellValue());//연수
		    	String col06 = row.getCell(6).getStringCellValue();
		    	String col07 = row.getCell(7).getStringCellValue();
		    	String col08 = row.getCell(8).getStringCellValue();
		    	String col09 = row.getCell(9).getStringCellValue();
//		    	String col10 = Boolean.toString(row.getCell(10).getBooleanCellValue());
//		    	String col11 = Double.toString(row.getCell(11).getNumericCellValue());
//		    	String col12 = row.getCell(12).getStringCellValue();
		    	
		    	//출력용 문자열 만들기
//		    	sb.append(col00+"="+col01+"="+col02+"="+col03+"="+col04+"="+col05+"="
//		    			+col06+"="+col07+"="+col08+"="+col09+"="+col10+"="+col11+"="
//		    			+col12+"<br>");
		    	
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
//		    	commonDTO.setCol10(col10);
//		    	commonDTO.setCol11(col11);
//		    	commonDTO.setCol12(col12);
		    	commonDTO.setFlag(strToday);
		    	commonDTO.setGubun("gil");
		    	
		    	//insert 
		    	if(!col01.equals("") && !col02.equals(""))
		    		dao.insert(commonDTO);
		    }
		    
		    //model.addAttribute("sb", sb);
		    workbook.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("업로드 실패 or Excel파일 읽기 실패");
		}
		
		return "sbadmin/print01";
	}	
	
	//DB에 등록된 엑셀 내용을 리스트로 출력 - 롤제단
	/**
	flag를 중복 제거해서 가져온 후 가장 최근에 올린걸 목록으로 출력해야 함.	
	 */
	@RequestMapping("/admin34/print02.do")
	public String sbadmin_print02(Model model, HttpServletRequest req) {
		//파라미터
		String flagNum = req.getParameter("flagNum");
		String searchNum = req.getParameter("searchNum");
		//DTO에 저장
		CommonDTO dto = new CommonDTO();		
		dto.setGubun("roll");//롤제단
		dto.setSearchNum(searchNum);
		
		//입력된 데이터 중 flag(등록한시각)를 중복제거한 후 인출한다. 
		List<CommonDTO> flagList = dao.groupByFlag();
//		System.out.println("flagList="+ flagList);
		model.addAttribute("flagList", flagList);
				
		try {	
			if(flagNum==null || flagNum.equals("")) {
				//DTO에 저장 : 가장 최근에 등록한 내역
				dto.setFlag(flagList.get(0).getFlag());
			}
			else {
				//DTO에 저장 : 파라미터에서 선택한 내역
				dto.setFlag(flagNum);
			}
			System.out.println("dto="+ dto);
			model.addAttribute("rows", dao.selectExcel(dto));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sbadmin/print02";
	}
	@RequestMapping("/admin34/print03.do")
	public String sbadmin_print03(Model model, HttpServletRequest req) {
		//파라미터
		String flagNum = req.getParameter("flagNum");
		String searchNum = req.getParameter("searchNum");
		//DTO에 저장
		CommonDTO dto = new CommonDTO();		
		dto.setGubun("gil");//길로틴
		dto.setSearchNum(searchNum);
		
		//입력된 데이터 중 flag(등록한시각)를 중복제거한 후 인출한다. 
		List<CommonDTO> flagList = dao.groupByFlag();
		model.addAttribute("flagList", flagList);
		
		try {
			if(flagNum==null || flagNum.equals("")) {
				//DTO에 저장 : 가장 최근에 등록한 내역
				dto.setFlag(flagList.get(0).getFlag());
			}
			else {
				//DTO에 저장 : 파라미터에서 선택한 내역
				dto.setFlag(flagNum);
			}
			model.addAttribute("rows", dao.selectExcel(dto));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sbadmin/print03";
	}
	//프린트 팝업창 - 롤제단
	@RequestMapping("/admin34/onlyPrint02.do")
	public ModelAndView sbadmin_onlyPrint02(CommonDTO commonDTO, ModelAndView mv) {
		/*
		발주처 : 상호 
		지종 : 지종
		평량 : 규격 - 첫번째
		규격 : 사이즈및수량 - 첫번째 
		수량 : 직접입력
		품명 : 품명
		입고처 : 도착지
		출고일 : 날짜 
		*/
		commonDTO = dao.selectOne(commonDTO);		
		mv.addObject("row", commonDTO);
		
		Map<String, String> otherInfo = new HashMap<>();
		//평량
		String[] c04Arr = commonDTO.getCol04().split(" ");
		otherInfo.put("pyeong", c04Arr[0]);
		//규격
		String[] c07Arr = commonDTO.getCol07().split(" ");
		otherInfo.put("gyugyeog", c07Arr[0]);
		//품명
		otherInfo.put("productName", commonDTO.getCol10());
		//입고처
		otherInfo.put("arrivalName", commonDTO.getCol11());
		//출고일
		otherInfo.put("outDate", commonDTO.getCol09());
		
		mv.addObject("otherInfo", otherInfo);
		mv.setViewName("sbadmin/onlyPrint");
		return mv;
	}	
	//프린트 팝업창 - 길로틴
	@RequestMapping("/admin34/onlyPrint03.do")
	public ModelAndView sbadmin_onlyPrint03(CommonDTO commonDTO, ModelAndView mv) {
		/*
		발주처 : 상호 
		지종 : 지종
		평량 : 규격의 첫번째
		규격 : 재단사이즈의 첫번째(콤마로구분)
		수량 : 직접입력
		품명 : 품명
		입고처 : 도착지
		출고일 : 날짜 
		 */
		commonDTO = dao.selectOne(commonDTO);		
		mv.addObject("row", commonDTO);
		
		Map<String, String> otherInfo = new HashMap<>();
		//평량
		String[] c04Arr = commonDTO.getCol04().split(" ");
		otherInfo.put("pyeong", c04Arr[0]);
		//규격
		String[] c06Arr = commonDTO.getCol06().split(",");
		otherInfo.put("gyugyeog", c06Arr[0]);
		//품명
		otherInfo.put("productName", commonDTO.getCol08());
		//입고처
		otherInfo.put("arrivalName", commonDTO.getCol09());
		//출고일
		otherInfo.put("outDate", commonDTO.getCol00());
		
		mv.addObject("otherInfo", otherInfo);
		mv.setViewName("sbadmin/onlyPrint");
		return mv;
	}	
	@RequestMapping("/admin34/directInput.do")
	@ResponseBody
	public String directInput(HttpServletRequest req) {
		
		String idx = req.getParameter("idx");
		String etc01 = req.getParameter("etc01");
		
		Map<String, String> maps = new HashMap<>();
		maps.put("idx", idx);
		maps.put("etc01", etc01);
		
		int result = dao.dataUpdate(maps);		
		return "update결과:"+ result;
	}
	
}
