package RhythmGame;

import javax.swing.JFrame;

import chat.Server;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 4;
	public static final int SLEEP_TIME = 10;
	public static final int REACH_TIME = 2;

	public static int combo = 0; // �޺�
	public static int myEnergy = 10; // ü��

	public static boolean isLogin = false; // �α��� ����

	public static long wheelTime = 0; // �� ������ ���� �� ������ ���۽ð��� �����ϴ� ����

	public static String judge; // ���� ����
	public static String enemyJudge; // ��� ����
	public static String printCombo = "Combo: " + combo; // "combo= x"�� �����ϴ� ����

	public static DynamicBeat dynamicBeat;
	
	public static void main(String[] args) {
		new Server();
		dynamicBeat = new DynamicBeat();
	}
}
