package actions;

import java.io.File;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OptFileAction extends ActionSupport{
	

	private static Logger logger = LogManager.getLogger(OptFileAction.class);  
	//返回给前端的信息
	private String fileInfo;
	
	public String getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}

	//项目根路径如D:\xxx\项目名  
	protected static String basepath = ServletActionContext.getServletContext().getRealPath(File.separator);
	protected static String filepath = basepath+File.separator+"pic_label"+File.separator+"img";
	
	//获取的文件列表
	protected static File imgFile = new File(filepath);
	protected static File[] imgs = imgFile.listFiles();
	
	//设置成静态变量即可实现共享变量,设置成数组可以自动初始化
	//此数组和文件imgs[]数组一一对应
	//0 代表还有任务  1代表正在被使用  2代表该文件夹已经完成
	protected static int[] arrays  = new int[imgs.length]; 
	
	public String execute(){
		
		//判断用户需要去读哪个文件夹
		for(int i=0;i<arrays.length;i++){
			if (arrays[i]==0) {
				if (imgs[i].listFiles().length!=0) {
					//设置本文件已经被用户读取
					arrays[i]=1;

					//把file[i]文件夹的具体信息给前端,包括 文件filename，里面所有图片的name[]数组
					//格式如下
					/*{
						filename:3,
						picnames:[3.0,3.3]
					}*/
					String filename = imgs[i].getName();
					File[] pics = imgs[i].listFiles();
					
					ArrayList<String> names = new ArrayList<>();
					for(int j=0;j<pics.length;j++){
						names.add(pics[j].getName());
					}
					
					//封装成json数据
					JSONObject fileobject = new JSONObject();
					fileobject.put("filename", filename);
					fileobject.put("picnames",JSONArray.fromObject(names));
					fileobject.put("number", i);
					
					fileInfo = fileobject.toString();
					logger.warn("(一类)系统分配当前任务信息：   文件夹："+filename+"，图片数："+names.size());
					break;
				}else {
					//该文件下没有图片,设置文件夹状态为2
					arrays[i] = 2;
				}
			}
		}
		return SUCCESS;
	}
}
