
//國井作成
import java.util.Random;

public class DiscussionLeader {
	private static int num;

	public static void main(String[] args) {
		System.out.println("本日の担当");
		GroupA();
		GroupB();
		GroupC();
	}

	//-----------------------------------------------------------
	//グループAの計算
	static void GroupA() {
		Random rand = new Random();
		num = rand.nextInt(4);

		switch (num) {
		case 0:
			System.out.println("第1グループ:國井");
			break;

		case 1:
			System.out.println("第1グループ:青木");
			break;
		case 2:
			System.out.println("第1グループ:松崎");
			break;
		case 3:
			System.out.println("第1グループ:白濵");
			break;
		}
	}

	//-----------------------------------------------------------
	//グループBの計算
	static void GroupB() {
		Random rand = new Random();
		num = rand.nextInt(4);

		switch (num) {
		case 0:
			System.out.println("第2グループ:石井");
			break;

		case 1:
			System.out.println("第2グループ:前田");
			break;
		case 2:
			System.out.println("第2グループ:齋藤");
			break;
		case 3:
			System.out.println("第2グループ:田名網");
			break;
		}
	}

	//-----------------------------------------------------------
	//グループCの計算
	static void GroupC() {
		Random rand = new Random();
		num = rand.nextInt(5);

		switch (num) {
		case 0:
			System.out.println("第3グループ:橋口");
			break;

		case 1:
			System.out.println("第3グループ:平山");
			break;
		case 2:
			System.out.println("第3グループ:上脇");
			break;
		case 3:
			System.out.println("第3グループ:白井");
			break;
		case 4:
			System.out.println("第3グループ:林");
			break;
		}
	}
}
