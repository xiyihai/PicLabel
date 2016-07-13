package dataprocess;

import java.io.File;
import java.util.Date;

public class CnnName {
		
		//这个函数针对目录下所有文件夹及其下面的文件进行重命名,格式：0.1.i.jpg(e代表)
		public void pic_name_converter(String source){
			
			File source_dir = new File(source);
			//获取目录下的所有文件夹，这里注意出现的顺序不是磁盘顺序,把e q r t w这些都修改成 0 1 2 3 4....cnn只能识别这些数字
			File[] dir_files = source_dir.listFiles();
			
			for(int i=0;i<dir_files.length;i++){
				File dir_files_new = new File(source+File.separator+i);
				if(!dir_files[i].renameTo(dir_files_new)){
					System.out.println(dir_files[i].getName()+"处理失败");
				}else {
					System.out.println(dir_files[i].getName()+" 转换成: "+dir_files_new.getName());
				}
			}
			
			for(int i=0;i<dir_files.length;i++){
				
				//1 2 3 4 5 6 pic2
				File[] dir2_files = dir_files[i].listFiles();
				
				for(int j=0;j<dir2_files.length;j++){
					
					File[] imgs = dir2_files[j].listFiles();
			
					System.out.println(dir_files[i].getName()+"/"+dir2_files[j].getName()+"/"+imgs.length+"个图片");
					
					for(int k=0;k<imgs.length;k++){
						File newname_pic = new File(dir2_files[j].getAbsolutePath()+File.separator+
								dir_files[i].getName()+"."+dir2_files[j].getName()+"."+String.valueOf(k)+".jpg");
						if(!imgs[k].renameTo(newname_pic)){
							System.out.println(imgs[k].getName()+"处理失败");
						}
					}
				}
			}
			
		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			System.out.println("begin==============================="+new Date().getMinutes());
			new CnnName().pic_name_converter("F:\\足球图片\\labels");
			System.out.println("success==============================="+new Date().getMinutes());
		}
}
