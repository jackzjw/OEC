package shangchuan.com.oec.util;

import java.util.Comparator;

import shangchuan.com.oec.model.bean.UserInfoBean;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<UserInfoBean> {

	public int compare(UserInfoBean o1, UserInfoBean o2) {
		if (o1.getSortLetter().equals("@")
				|| o2.getSortLetter().equals("#")) {
			return -1;
		} else if (o1.getSortLetter().equals("#")
				|| o2.getSortLetter().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetter().compareTo(o2.getSortLetter());
		}
	}

}
