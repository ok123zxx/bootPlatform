package com.springboot.myapp.web;

import com.springboot.base.utils.JedisUtils;
import com.springboot.base.utils.LogUtils;
import com.springboot.base.utils.Underline2Camel;
import com.springboot.myapp.exception.BaseException;
import com.springboot.myapp.service.PaChongService;
import com.springboot.myapp.service.SpringBootService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value = "/base")
public class SpringBootController {

	@Autowired
	private SpringBootService bootService;

	@Autowired
	private PaChongService paChongService;

	@RequestMapping("/down")
	public Object down(String name) throws Exception{
		paChongService.run(name);
		return "success";
	}

	/*
	 * 驼峰转下划线
	 */
	@RequestMapping("/camel2Undeline")
	public Object camel2Undeline(String str, HttpServletRequest request){
        if(StringUtils.isBlank(str) || str.length() > 500){
            return null;
        }
        return Underline2Camel.camel2Underline(str);
	}

	/*
	 * 下划线转驼峰
	 */
	@RequestMapping("/undeline2Camel")
	public Object undeline2Camel(String str,Boolean small){
        if(StringUtils.isBlank(str) || str.length() > 500){
            return null;
        }
        return Underline2Camel.underline2Camel(str,small);
	}

	@RequestMapping("/echo")
	public String echo(String name){
		return "echo "+name;
	}

	@RequestMapping(value = "/getName")
	public String getName() {
		return bootService.getName();
	}
	
	@RequestMapping(value = "/getException")
	public String getException() {
		if(true) {
			throw new BaseException();
		}
		return "如果你看到这条信息就炸了";
	}

	@RequestMapping(value = "/redis")
	public String redisTest(String key){
		if("caion".equals(key)){
			JedisUtils.set(key,"zzzxxx",100);
			return JedisUtils.get(key);
		}
		return "fail";
	}

	@RequestMapping(value = "/upload")
	public Object updateFile(HttpServletRequest request){
		List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				.getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					String uploadFilePath = file.getOriginalFilename();
					System.out.println("uploadFlePath:" + uploadFilePath);
					// 截取上传文件的文件名
					String uploadFileName = uploadFilePath
							.substring(uploadFilePath.lastIndexOf('\\') + 1,
									uploadFilePath.indexOf('.'));
					System.out.println("multiReq.getFile()" + uploadFileName);
					// 截取上传文件的后缀
					String uploadFileSuffix = uploadFilePath.substring(
							uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
					System.out.println("uploadFileSuffix:" + uploadFileSuffix);
					File dictionary = new File("H:\\uploadFiles");
					if(!dictionary.exists()){
						dictionary.mkdirs();
					}
					File newFile = new File( dictionary.getPath()+File.separator+ uploadFileName + "." + uploadFileSuffix);
					if(!newFile.exists()){
						newFile.createNewFile();
					}
					stream = new BufferedOutputStream(new FileOutputStream(newFile));
					byte[] bytes = file.getBytes();
					stream.write(bytes,0,bytes.length);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (stream != null) {
							stream.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("上传文件为空");
			}
		}
		System.out.println("文件接受成功了");
		return "success";
	}

	@RequestMapping(value = "/download")
	public void downloadFile(String fileName,HttpServletResponse response){
		LogUtils.warnPrint("begindown:"+fileName);
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File("/home/files/"
					+ fileName)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			LogUtils.errorPrint("",e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		LogUtils.warnPrint("downSuccess:"+fileName);
	}



//	@RequestMapping(value = "/testUploadFile", method = RequestMethod.POST)
//	public void testUploadFile(HttpServletRequest req,
//							   MultipartHttpServletRequest multiReq) {
//		// 获取上传文件的路径
//		String uploadFilePath = multiReq.getFile("file1").getOriginalFilename();
//		System.out.println("uploadFlePath:" + uploadFilePath);
//		// 截取上传文件的文件名
//		String uploadFileName = uploadFilePath.substring(
//				uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
//		System.out.println("multiReq.getFile()" + uploadFileName);
//		// 截取上传文件的后缀
//		String uploadFileSuffix = uploadFilePath.substring(
//				uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
//		System.out.println("uploadFileSuffix:" + uploadFileSuffix);
//		FileOutputStream fos = null;
//		FileInputStream fis = null;
//		try {
//			fis = (FileInputStream) multiReq.getFile("file1").getInputStream();
//			fos = new FileOutputStream(new File(".//uploadFiles//" + uploadFileName
//					+ ".")
//					+ uploadFileSuffix);
//			byte[] temp = new byte[1024];
//			int i = fis.read(temp);
//			while (i != -1){
//				fos.write(temp,0,temp.length);
//				fos.flush();
//				i = fis.read(temp);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (fos != null) {
//				try {
//					fos.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
}
