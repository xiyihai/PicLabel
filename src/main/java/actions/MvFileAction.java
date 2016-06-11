package actions;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class MvFileAction extends ActionSupport{
	
	private static Logger logger = LogManager.getLogger(MvFileAction.class);  
	
	//接收前端的数据
	private String filename;
	private String name;
	private String keyCode;
	private int category;
	private int number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	
	public String execute(){
		
		//需要把图片移动到对应的位置
		//图片原来的路径
		String picPath;
		
		//图片所需移动到的路径
		String mvPath;
		
		if(category==1){
			picPath = OptFileAction.filepath+File.separator+filename+File.separator+name;
			mvPath = OptFileAction.basepath+File.separator+"pic_label"+File.separator+"labels"+
					File.separator+keyCode+File.separator+"pic2"+File.separator+name;
			
			//需要判断移入新的图片，能否让之前(二类)state为2的文件夹复苏为0，这样才能继续二类标注
			for(int i=0;i<Opt2FileAction.imgs.length;i++){
				if (Opt2FileAction.imgs[i].getName().equals(keyCode) && Opt2FileAction.arrays[i] == 2) {
					Opt2FileAction.arrays[i] = 0;
					logger.warn("由于新加入图片导致(二类)文件夹从状态2复苏为0,文件夹名称："+keyCode);
					logger.error("(二类)文件夹状态改变如下：  "+"文件夹名称:"+Opt2FileAction.imgs[i].getName()+",修改状态为：0");
					break;
				}
			}
			
		}else {
			picPath = Opt2FileAction.filepath+File.separator+filename+File.separator+"pic2"+File.separator+name;
			mvPath = Opt2FileAction.basepath+File.separator+"pic_label"+File.separator+"labels"+
					File.separator+filename+File.separator+keyCode+File.separator+name;
		}
		
		File pic_ori = new File(picPath);
		File pic_mv = new File(mvPath);	
		
		if(!pic_ori.renameTo(pic_mv)){
			logger.warn("未成功标注图片信息如下："+"图片原路径："+picPath+"图片目的路径："+mvPath);
		}
		
		return SUCCESS;
	}
}
