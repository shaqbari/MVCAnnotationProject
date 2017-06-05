package com.sist.controller;

// com.sist.model.MainController
import java.io.*;

/**
 * @author user
 *	cmd.xml���� 
	 *<beans>
	  <context:component-scan base-package="com.sist.model"/>
	</beans>
 *	�� ��Ű���� �дµ� ���� Ŭ����
 *
 *	���� ��� �� ��Ű���� �ִ� Ŭ���� MainController.java��
 *	reflection���� �޸𸮿� �ø� �� �ְ�  com.sist.model.MainController�� ����� �ش�.
 * */
public class FileConfig {
	public static void main(String[] arg) {
		FileConfig fc = new FileConfig();
		fc.componentScan("com.sist.model");//Ž���� ��Ű���� �Ű������� ����
		
		
	}

	public void componentScan(String pack) {//Ž���� ��Ű���� �Ű������� ����
		try {
			String path = "C:\\webDev\\webDev\\MVCAnnotationProject\\src\\";
			path = path + pack.replace(".", "\\");
			File dir = new File(path);
			File[] files = dir.listFiles(); //��� ���丮�� ���ϵ��� ������ �´�. �ڹٴ� ���丮�� ���Ϸ� �����.
			for (File f : files) {
				// System.out.println(f.getName());
				String str = pack + "." + f.getName().substring(0, f.getName().lastIndexOf("."));//Ȯ���ڸ� �� ���ϸ��� �д´�.

				System.out.println(str);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
