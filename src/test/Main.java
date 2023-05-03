package test;

import view.PikachuView;

public class Main {
	PikachuView frame;
	public Main() {
		frame = new PikachuView();
		Time time = new Time();
		time.start();
		new Thread(frame).start();

	}

	public static void main(String[] args) {
		new Main();
	}

	class Time extends Thread {

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (frame.isPause()) {
					if (frame.isResume()) {
						frame.timeL--;
					}
				} else {
					frame.timeL--;
				}
				if (frame.timeL == 0) {
					frame.showDialogNewGame("Full time\nBan co muon choi lai?", "Lose", 1);
				}
			}
		}
	}
}
