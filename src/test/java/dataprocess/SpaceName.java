package dataprocess;

import java.io.File;
import java.util.Date;

public class SpaceName {

	public void pic_name_converter(String source){
		
		File source_dir = new File(source);
		
			//1 2 3 4 5 6 7 8
			File[] dir2_files = source_dir.listFiles();
			
			for(int j=0;j<dir2_files.length;j++){
				//图片
				File[] imgs = dir2_files[j].listFiles();
				
				for(int k=0;k<imgs.length;k++){
					File newname_pic = new File(dir2_files[j].getAbsolutePath()+File.separator+
							"3."+dir2_files[j].getName()+"."+String.valueOf(k)+".jpg");
					if(!imgs[k].renameTo(newname_pic)){
						System.out.println(imgs[k].getName()+"处理失败");
					}
				}
			}
	}
	
	public static void main(String[] args){
		System.out.println("begin==============================="+new Date().getMinutes());
		new SpaceName().pic_name_converter("F:/football-CNN/football_video/space_second_train");
		System.out.println("success==============================="+new Date().getMinutes());
	}
}

