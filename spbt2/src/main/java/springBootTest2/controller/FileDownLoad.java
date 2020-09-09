package springBootTest2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Component
@Service
public class FileDownLoad {
	public void fileDownLoad(String path, String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String RealPath = request.getServletContext().getRealPath(path);
		String originalFileName = fileName.split("`")[1];
		String storeFileName = fileName.split("`")[0];
		originalFileName = URLEncoder.encode(originalFileName, "UTF-8");
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        File file = new File(RealPath + "/" + storeFileName);
        // 웹브라우저쪽으로 output하기 위해서 사용하는 객체
        ServletOutputStream out1 = null; 
        // 시스템에 있는 파일을 가져오기 위한 객체
        FileInputStream fis = null;
		try {
			// 웹브라우저에 전송할 수 있게 정보를 가져옴
			out1 = response.getOutputStream();
			fis = new FileInputStream(file);
			// fis -> out1에 복사
			// fis가 가지고 있는 파일을 out1에 의해 웹브라우저에 전송
	        FileCopyUtils.copy(fis, out1); // 전송
	        response.flushBuffer(); // response에 의해 전송이 된 후 response 버퍼를 지움
	        out1.flush();
	        out1.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			 if(fis != null){                 
	                try{ fis.close();}catch(Exception e){}
	            }
		}
	}
}
