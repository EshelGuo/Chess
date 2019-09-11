package com.eshel.chess.chess.socket.tasks;

import com.eshel.chess.chess.socket.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * createBy Eshel
 * createTime: 2018/8/26 22:11
 * desc: TODO
 */
public class ClientTask implements Runnable{
	Socket socket;
	private String TAG = ClientTask.class.getSimpleName();
	private InetAddress HOST;
	private BufferedReader in;
	private PrintWriter out;
	private boolean isConnected;
	public static volatile String msg;
	@Override
	public void run() {

		try {
			socket = new Socket("192.168.1.108", Config.PORT);
//			InputStream inputStream = socket.getInputStream();
//			BufferedReader bw = new BufferedReader(new InputStreamReader(inputStream));

			while (true){
//				SystemClock.sleep(1000);
				if(msg != null){
					OutputStream outputStream = socket.getOutputStream();
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
					bw.write(msg);
					bw.newLine();
					outputStream.close();
					msg = null;
				}
//				String s = bw.readLine();
//				if(s != null)
//					Util.toast(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*ScanDeviceTool sdt = new ScanDeviceTool();
		List<String> pList = sdt.scan();
		if(pList != null && pList.size() >0) {
			for (final String ip : pList) {
				new Thread() {
					@Override
					public void run() {
						try {
							HOST = InetAddress.getByName(ip);
							Log.d(TAG, "-------->" + ip);
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}
						try {
							socket = new Socket(HOST, PORT);
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (socket != null) {//如果无法建立连接，socket将为空
							try {
								in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							} catch (IOException e) {
								e.printStackTrace();
							}
							try {
								out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
							} catch (IOException e) {
								e.printStackTrace();
							}
							if (socket.isConnected()) {
								Log.d(TAG, "-------->conncted: " + ip);
								isConnected = true;
								out.print("我扫描到你了!!!");
								out.flush();

								while (true){
									try {
										String s = in.readLine();
										if(s != null)
											Util.toast(s);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}

					}
				}.start();
			}
		}*/
	}
}
