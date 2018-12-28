package com.sample.startup.gc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
	public static Context sContext;
	public static ProcessCpuTracker processCpuTracker = new ProcessCpuTracker(android.os.Process.myPid());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sContext = getApplicationContext();

		final Button testGc = (Button) findViewById(R.id.test_gc);
		testGc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				processCpuTracker.update();
				testGc();
				processCpuTracker.update();
				android.util.Log.e("ProcessCpuTracker1",
						processCpuTracker.printCurrentState(SystemClock.uptimeMillis()));
			}
		});

		final Button testIO = (Button) findViewById(R.id.test_io);
		testIO.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				processCpuTracker.update();
				testIO();
				processCpuTracker.update();
				android.util.Log.e("ProcessCpuTracker",
						processCpuTracker.printCurrentState(SystemClock.uptimeMillis()));
			}
		});

		final Button processOut = (Button) findViewById(R.id.test_process);
		processOut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				processCpuTracker.update();
				android.util.Log.e("ProcessCpuTracker",
						processCpuTracker.printCurrentState(SystemClock.uptimeMillis()));

			}
		});

	}

	private void testIO() {
		for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					writeSth();
				}
			}).start();
		}

	}

	private void testGc() {
		for (int i = 0; i < 10000; i++) {
			int[] test = new int[100000];
			System.gc();
		}
	}

	private void writeSth() {
		try {
			File f = new File(getFilesDir(), "a.txt");
			if (f.exists()) {
				f.delete();
			}
			byte[] data = new byte[4096001];
			for (int i = 0; i < data.length; i++) {
				data[i] = 'a';
			}
			FileOutputStream fos = new FileOutputStream(f);
			for (int i = 0; i < 30; i++) {
				fos.write(data);
			}

			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
