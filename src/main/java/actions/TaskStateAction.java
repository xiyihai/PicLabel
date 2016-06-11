package actions;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class TaskStateAction extends ActionSupport{
	
	private static Logger logger = LogManager.getLogger(TaskStateAction.class); 
	
	private int state;
	private int number;
	private int count;
	private int category;
	
	
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public String execute(){
		
		boolean isFull = false;
		boolean is2Full = false;
		
		if (category==1) {
			if(state==0 && OptFileAction.arrays[number] != 2 && count>0){
				logger.warn("(一类)当前用户本次任务共标注："+count+"张图片");
			}else if (state == 2) {
				logger.warn("(一类)当前用户已全部完成工作： "+"文件夹名称:"+OptFileAction.imgs[number].getName());
				
				if (OptFileAction.imgs[number].listFiles().length > 0 ) {
					logger.error("(一类)文件夹由于新加入了图片，任务状态不能变成已完成2：  "+"文件夹名称:"+OptFileAction.imgs[number].getName());
					isFull = true;
				}
			}else if (state == 1) {
				if (OptFileAction.arrays[number] == 1) {
					return ERROR;
				}else {
					if(count > 0){
						logger.warn("(一类)取消当前用户上一次标记的数量："+count+"张图片");	
					}
				}
			}
			if (OptFileAction.arrays[number] != 2) {
				if (isFull) {
					logger.warn("(一类)文件夹状态改变如下：  "+"文件夹名称:"+OptFileAction.imgs[number].getName()+",修改状态为：0");
					OptFileAction.arrays[number] = 0;	
				}else {
					logger.warn("(一类)文件夹状态改变如下：  "+"文件夹名称:"+OptFileAction.imgs[number].getName()+",修改状态为："+state);
					OptFileAction.arrays[number] = state;				
				}
			}	
		}else if (category == 2) {
			if(state==0 && Opt2FileAction.arrays[number] != 2 && count>0){
				logger.error("(二类)当前用户本次任务共标注："+count+"张图片");
			}else if (state == 2) {
				logger.error("(二类)当前用户已全部完成工作： "+"文件夹名称:"+Opt2FileAction.imgs[number].getName());
				
				File pic2 = new File(Opt2FileAction.imgs[number].getAbsolutePath()+File.separator+"pic2");
				if (pic2.listFiles().length > 0 ) {
					logger.error("(二类)文件夹由于新加入了图片，任务状态不能变成已完成2：  "+"文件夹名称:"+Opt2FileAction.imgs[number].getName());
					is2Full = true;
				}
			}else if (state == 1) {
				if (Opt2FileAction.arrays[number] == 1) {
					return ERROR;
				}else {
					if (count > 0) {
						logger.error("(二类)取消当前用户上一次标记的数量："+count+"张图片");			
					}
				}
			}
			if (Opt2FileAction.arrays[number] != 2) {
				if (is2Full) {
					logger.error("(二类)文件夹状态改变如下：  "+"文件夹名称:"+Opt2FileAction.imgs[number].getName()+",修改状态为：0");
					Opt2FileAction.arrays[number] = 0;
				}else {
					logger.error("(二类)文件夹状态改变如下：  "+"文件夹名称:"+Opt2FileAction.imgs[number].getName()+",修改状态为："+state);
					Opt2FileAction.arrays[number] = state;	
				}	
			}
		}
		
		return SUCCESS;
	}
}
