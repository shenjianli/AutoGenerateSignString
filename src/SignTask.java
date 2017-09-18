
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class SignTask extends TimerTask {

	@Override
	public void run() {
		String currentdate = "";
		String employeeId = "00000185";
		Calendar now = Calendar.getInstance();
		
		now = Calendar.getInstance();

		System.out.println("年: " + now.get(Calendar.YEAR));
		System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
		System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
		System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
		System.out.println("分: " + now.get(Calendar.MINUTE));
		System.out.println("秒: " + now.get(Calendar.SECOND));
		System.out.println("当前时间毫秒数：" + now.getTimeInMillis());
		System.out.println(now.getTime());
		// Scanner in = new Scanner(System.in);
		// hour = in.nextInt();

		Date nowdate = new Date();
		// format date pattern
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		currentdate = formatter.format(nowdate);

		Map<String, String> params = new HashMap<String, String>();
		params.put("currentdate", currentdate);
		params.put("employeeId", employeeId);
		params.put("inOutFlag", "0");

		String signIn = null;
		String signOut = null;
		String signInparamStr = EncryptUtils.getParameterStr(params);
		try {
			signIn = EncryptUtils.encode(signInparamStr);
			
			System.out.println("签到字符串:" + signIn);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> signOutparams = new HashMap<String, String>();
		signOutparams.put("currentdate", currentdate);
		signOutparams.put("employeeId", employeeId);
		signOutparams.put("inOutFlag", "1");

		String signOutParams = EncryptUtils.getParameterStr(signOutparams);
		try {
			signOut = EncryptUtils.encode(signOutParams);

			System.out.println("签退字符串:" + signOut);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (WriteStringToTxt.WriteStringToFile("./signin.txt", signIn)) {
			System.out.println("写入签到加密字符串文件成功");
		}
		if (WriteStringToTxt.WriteStringToFile("./signout.txt", signOut)) {
			System.out.println("写入签退加密字符串文件成功");
			System.out.print(">>");
		}
		System.out.println("自动产生签到，签退，加密串完成");
	}

}
