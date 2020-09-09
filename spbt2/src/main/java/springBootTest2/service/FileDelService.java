package springBootTest2.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.FileName;

@Component
@Service
public class FileDelService {

	public void fileSessionAdd(FileName fileName, HttpSession session, Model model) {
		int num = 0;
		List<FileName> list = (List<FileName>) session.getAttribute("fileList");
		
		if (list == null) {
			System.out.println("--- list null ---");
			list = new ArrayList<FileName>();
		}
		
		// session이 존재하지 않으면 true, sesssion이 존재하면 false
		Boolean newFile = true;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStoreFileName().equals(fileName.getStoreFileName())) {
				System.out.println("--- list remove ---");
				list.remove(i);
				newFile = false;
				num = 0;
				break;
			}
		}
		
		if (newFile) {
			System.out.println("--- list add ---");
			list.add(fileName);
			num = 1;
		}
		
		session.setAttribute("fileList", list);
		model.addAttribute("val", num);
	}
	
}
