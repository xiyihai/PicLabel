package dataprocess;

import java.io.File;
import java.util.Date;


public class PicNameProcess {

	//这个函数使得文件夹和里面的图片名字前缀保持相同
	public void pic_name_syn(String source){
		File source_dir = new File(source);
		
		//获取目录下的所有文件夹
		File[] dir_files = source_dir.listFiles();
		
		for(int i=0;i<dir_files.length;i++){
			
			System.out.print("正在处理："+dir_files[i].getName()+" ");
			
			//对每一个文件夹下的文件也需要重命名
			File[] pics = dir_files[i].listFiles();
	
			System.out.println(pics.length+"个图片");
			
			for(int j=0;j<pics.length;j++){
				File newname_pic = new File(dir_files[i].getAbsolutePath()+File.separator+dir_files[i].getName()+"."+String.valueOf(j)+".jpg");
				pics[j].renameTo(newname_pic);
			}
			
		}
	
	}
	
	//这个函数针对目录下所有文件夹及其下面的文件进行重命名
	public void pic_name_converter(String source){
		File source_dir = new File(source);
		
		//获取目录下的所有文件夹，这里注意出现的顺序不是磁盘顺序,而是数字顺序  809在9之前出现
		File[] dir_files = source_dir.listFiles();
		
		
		for(int i=0;i<dir_files.length;i++){
			
			File newname_dir = new File(source+File.separator+String.valueOf(i));
			
			System.out.print("正在处理："+dir_files[i].getName()+" ");
			
			//对每一个文件夹下的文件也需要重命名
			File[] pics = dir_files[i].listFiles();
	
			System.out.println(pics.length+"个图片");
			
			for(int j=0;j<pics.length;j++){
				File newname_pic = new File(dir_files[i].getAbsolutePath()+File.separator+String.valueOf(i)+"."+String.valueOf(j)+".jpg");
				pics[j].renameTo(newname_pic);
			}

			//renameTo函数重命名之后，不再指向原来那个文件，所以要最后修改
			//切注意要是newname_dir已经存在，则此方法失败
			System.out.println(dir_files[i].renameTo(newname_dir));
			
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("begin==============================="+new Date().getMinutes());
		new PicNameProcess().pic_name_converter("E:/video-pic-conventer");
		//new PicNameProcess().pic_name_syn("E:/足球比赛视频/pic");
		System.out.println("success==============================="+new Date().getMinutes());
	}

}
