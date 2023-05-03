package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PikachuView extends JFrame implements ActionListener, Runnable {
	private JProgressBar time;
	public JLabel score;
	private JButton newgame_Button;
	private JButton reset_Button;
	private PikachuButton graphicsPanel;
	private JPanel timeandgraphics;
	private JPanel mainPanel;
	private boolean pause = false;
	private boolean resume = false;
	private int MAX_TIME = 50000;
	public int timeL = MAX_TIME;

	public PikachuView() {
		add(mainPanel = createMainPanel());
		setTitle("Pokemon Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 600);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createTimeAndGraphicsPanel(), BorderLayout.CENTER);
		panel.add(createMenuPanel(), BorderLayout.EAST);
		return panel;
	}

	private JPanel createGraphicsPanel() {
		graphicsPanel = new PikachuButton(this, 8, 10);
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.gray);
		panel.add(graphicsPanel);
		return panel;
	}

	private JPanel createTimePanel() {
		time = new JProgressBar(0, 100);
		time.setValue(100);
		time.setForeground(Color.GREEN);

		Font font = new Font("Arial", Font.BOLD, 24);
		JLabel jLabel = new JLabel("Time:");
		jLabel.setFont(font);
		JPanel panel = new JPanel(new BorderLayout(10, 5));
		panel.add(jLabel, BorderLayout.WEST);
		panel.add(time, BorderLayout.CENTER);
//		panel.add(createGraphicsPanel(),BorderLayout.SOUTH);
		return panel;
	}

	private JPanel createTimeAndGraphicsPanel() {
		timeandgraphics = new JPanel(new BorderLayout());
		timeandgraphics.add(createTimePanel(), BorderLayout.NORTH);
		timeandgraphics.add(createGraphicsPanel(), BorderLayout.CENTER);
		return timeandgraphics;
	}

	private JPanel createMenuPanel() {
		Font font = new Font("Arial", Font.BOLD, 30);
		score = new JLabel("0",JLabel.CENTER);
		score.setSize(50,50);
		score.setFont(font);
		
		JPanel panelScore = new JPanel(new GridLayout(2, 1));
		panelScore.add(new JLabel("Score:",JLabel.CENTER));
		panelScore.add(score);

		JPanel panelButton = new JPanel(new GridLayout(2, 1, 0, 5));
		panelButton.add(newgame_Button = new JButton("NEWGAME"));
		newgame_Button.addActionListener(this);

		JPanel panelControl = new JPanel(new BorderLayout(10, 10));
		panelControl.setBorder(new EmptyBorder(10, 3, 5, 3));
		panelControl.add(panelScore, BorderLayout.CENTER);
		panelControl.add(panelButton, BorderLayout.PAGE_END);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder("Pika"));
		panel.add(panelControl, BorderLayout.PAGE_START);

		return panel;
	}

	public void newGame() {
		timeL = MAX_TIME;
		graphicsPanel.removeAll();
		mainPanel.add(createTimeAndGraphicsPanel(), BorderLayout.CENTER);
		mainPanel.setVisible(true);
		score.setText("0");
	}

	public void showDialogNewGame(String message, String title, int t) {
		pause = true;
		resume = false;
		int select = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if (select == JOptionPane.YES_OPTION) {
			pause = false;
			newGame();
		} else {
			if (t == 1) {
				System.exit(0);
			} else {
				resume = true;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newgame_Button) {

			showDialogNewGame("Bạn có muốn chơi lại?", "Warning", 0);
		}
	}

	public boolean isResume() {
		return resume;
	}

	public void setResume(boolean resume) {
		this.resume = resume;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time.setValue((int) ((double) timeL / MAX_TIME * 100));
			if(time.getValue()<=60&&time.getValue()>30) {
				time.setForeground(Color.YELLOW);
			}
			if(time.getValue()<=30) {
				time.setForeground(Color.RED);
			}
		}
	}

}
