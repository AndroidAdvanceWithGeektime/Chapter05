package com.sample.startup.gc.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellHelper {

	private static final String TAG = "ShellHelper";

	/**
	 * 执行shell命令
	 */
	public static String executeShellCommand(String command) {

		String result = "";

		Process process = null;
		DataOutputStream os = null;
		BufferedReader osReader = null;
		BufferedReader osErrorReader = null;

		try {
			//执行命令
			process = Runtime.getRuntime().exec(command);

			//获得进程的输入输出流
			os = new DataOutputStream(process.getOutputStream());
			osReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			osErrorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			//输入 exit 命令以确保进程退出
			os.writeBytes("exit\n");
			os.flush();

			String shellMessage;

			//获取命令执行信息
			shellMessage = readOSMessage(osReader);

			result = shellMessage;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (osReader != null) {
				try {
					osReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (osErrorReader != null) {
				try {
					osErrorReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (process != null) {
				process.destroy();
			}
		}

		return result;

	}

	//读取执行命令后返回的信息
	private static String readOSMessage(BufferedReader messageReader) throws IOException {
		StringBuilder content = new StringBuilder();
		String lineString;
		while ((lineString = messageReader.readLine()) != null) {
			System.out.println("lineString : " + lineString);
			content.append(lineString).append("\n");
		}
		return content.toString();
	}

}
