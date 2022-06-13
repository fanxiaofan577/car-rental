package cn.paopao.zuchebackend.controller.common;
/**
 * 图片统一查看控制器
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/File")
@Controller
public class PhotoController {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Value("${message.upPath}")
	private String uploadPhotoPath;//文件保存位置
	
	/**
	 * 系统统一的图片查看方法
	 * @param imageName
	 * @return
	 */
	@RequestMapping(value="/downloadImage")
	@ResponseBody
	public ResponseEntity<?> viewPhoto(@RequestParam(name="imageName",required=true)String imageName){
		Resource resource = resourceLoader.getResource("file:" + uploadPhotoPath + imageName);
		try {
			return ResponseEntity.ok(resource);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
