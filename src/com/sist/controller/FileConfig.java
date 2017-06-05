package com.sist.controller;

// com.sist.model.MainController
import java.io.*;

/**
 * @author user
 *	cmd.xml에서 
	 *<beans>
	  <context:component-scan base-package="com.sist.model"/>
	</beans>
 *	로 패키지를 읽는데 사용될 클래스
 *
 *	예를 들어 이 패키지에 있는 클래스 MainController.java를
 *	reflection으로 메모리에 올릴 수 있게  com.sist.model.MainController로 만들어 준다.
 * */
public class FileConfig {
	public static void main(String[] arg) {
		FileConfig fc = new FileConfig();
		fc.componentScan("com.sist.model");//탐색할 패키지를 매개변수로 전달
		
		
	}

	public void componentScan(String pack) {//탐색할 패키지를 매개변수로 전달
		try {
			String path = "C:\\webDev\\webDev\\MVCAnnotationProject\\src\\";
			path = path + pack.replace(".", "\\");
			File dir = new File(path);
			File[] files = dir.listFiles(); //경로 디렉토리의 파일들을 가지고 온다. 자바는 디렉토리도 파일로 여긴다.
			for (File f : files) {
				// System.out.println(f.getName());
				String str = pack + "." + f.getName().substring(0, f.getName().lastIndexOf("."));//확장자를 뺀 파일명을 읽는다.

				System.out.println(str);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
