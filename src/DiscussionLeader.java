import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DiscussionLeader {

	public static void main(String[] args) throws IOException {
		GroupMgr gMgr = new GroupMgr();
		System.out.println(gMgr.getFaciritatorMessage());
	}
}

class GroupMgr {
	private static Group[] initGroupList = {
			new Group(1, "古川 未羽", "廣瀨 翔", "服部 楓也", "瀬戸口 創", "早川 裕也"),
			new Group(2, "前野 颯希", "有澤 恭兵", "中島 実奈美", "船尾 竜史", "大和田 瑞稀"),
			new Group(3, "斎藤 さくら", "松田 教賢", "外瀬 文音", "綿貫 伊吹")
	};

	private Group[] groupList;

	GroupMgr() throws IOException {
		// JSONファイルを読み込んでgroupListにセットする
		// ファイルがなかったら、initGroupListをgroupListにコピーしてJSONに書き出す

		File file = new File("groups.json");
		if (file.exists()) {
			groupList = readGroupsFromJson(file);
		} else {
			groupList = initGroupList;
			writeGroupsToJson(groupList, file);
		}
	}

	public String getFaciritatorMessage() throws IOException {
		StringBuffer sb = new StringBuffer();
		for (Group g : groupList) {
			sb.append("グループ").append(g.getGroupNum())
					.append("のファシリテーターは")
					.append(g.assignFacilitator())
					.append("さんです！\n");
		}
		File file = new File("groups.json");

		//アサインしたら最新の情報をファイルに書き出す
		writeGroupsToJson(groupList, file);

		return sb.toString();
	}

	private void writeGroupsToJson(Group[] groups, File file) throws IOException {
		//JSONに書き出す
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(file, groups);
	}

	private Group[] readGroupsFromJson(File file) throws IOException {
		//JSONから読み出す
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(file, Group[].class);
	}
}

class Group {
	private static Random rand = new Random();
	private int groupNum;
	private Member[] members;

	//コンストラクター
	//可変長でメンバー名リストを受け取る
	public Group(int groupNum, String... nameList) {
		super();
		this.groupNum = groupNum;
		members = new Member[nameList.length];
		for (int i = 0; i < nameList.length; i++) {
			this.members[i] = new Member(nameList[i], 0);
		}
	}

	private Group() {
	}

	//ファシリテーターを今回アサインしていない人から乱数で選ぶ
	public String assignFacilitator() {
		List<Member> mList = new ArrayList<>();

		//まだアサインされていない人のリストを作成する
		for (Member m : members) {
			if (!m.isThisTurnAssgined()) {
				mList.add(m);
			}
		}
		//まだアサインされていない人がいなかったら、アサインフラグをリセットし
		//選択対象のリストに全員を入れる
		if (mList.isEmpty()) {
			for (Member m : members) {
				m.setThisTurnAssgined(false);
				mList.add(m);
			}
		}

		//アサインされていない人のリストから、乱数で選ぶ
		int index = rand.nextInt(mList.size());
		Member m = mList.get(index);

		//アサインフラグのONにする
		m.setThisTurnAssgined(true);

		//アサイン回数をカウントアップする
		m.countUpAssigns();

		return m.getName();
	}

	public int getGroupNum() {
		return this.groupNum;
	}

	public Member[] getMembers() {
		return members;
	}

	public void setMembers(Member[] members) {
		this.members = members;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

}

class Member {
	private String name;
	private int numFacilitatorAssigns;
	private boolean isThisTurnAssgined = false;

	public boolean isThisTurnAssgined() {
		return isThisTurnAssgined;
	}

	public void countUpAssigns() {
		numFacilitatorAssigns++;
	}

	public void setThisTurnAssgined(boolean isThisTurnAssgined) {
		this.isThisTurnAssgined = isThisTurnAssgined;
	}

	public Member(String name, int numFacilitatorAssigns) {
		super();
		this.name = name;
		this.numFacilitatorAssigns = numFacilitatorAssigns;
	}

	public Member() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumFacilitatorAssigns() {
		return numFacilitatorAssigns;
	}

	public void setNumFacilitatorAssigns(int numFacilitatorAssigns) {
		this.numFacilitatorAssigns = numFacilitatorAssigns;
	}
}