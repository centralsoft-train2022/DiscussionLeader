
import java.util.Random;

public class DiscussionReader
{
	private static String[][] member = new String[][]{
		{"國井", "青木" ,"松崎", "白濵", "空白"},
		{"石井", "前田" , "齋藤", "田名網", "空白"},
		{"橋口", "平山" , "上脇", "白井", "林"}
	};
	// 例 member[0][0]は"國井"
	//      member[1][0]は"石井"，member[1][1]は"前田"，member[1][2]は"齋藤"  

	private static int num;
	private static int index_group;
	
	public static void main(String[] args) 
	{
		System.out.println("本日の担当");
		Group(1, 4);     			// 第１グループAのリーダー選出
		Group(2, 4);     			// 第２グループBのリーダー選出
		Group(3, 5);      			// 第３グループCのリーダー選出
	       //  Group(引数１，引数２）:    Group( int group_num,   int num_members  )
	       //  引数１はグループ番号で group_num, 引数２はグループの人数で num_member
	}
	
	//-----------------------------------------------------------
	//
	static void Group(int group_num, int num_members)
	{
		        Random rand = new Random(); 
			num = rand.nextInt(num_members);// グループの人数 num_members は 4 あるいは 5 ，
							// 4の場合は 0,1,2,3 のどれかがnumに，
							// 5の場合は0,1,2,3,4のどれかがnumに代入

			index_group = group_num-1; // 2次元配列members[][]の１つ目のインデックス．
						//  groupn_numが 1か2か3 なので，
						//  index_groupは 0か1か2 
			System.out.println(group_num + "グループ：" + member[index_group][num]);
				// 例えば，group_num が 2 の場合，乱数 num に 0 が代入されると，
				//       index_groupは 2-1 で 1 になり，
				//       member[index_group][num] は member[1][0] で"石井" となり，
				//             「第 2 グループ：石井」が表示	
	}

}	



