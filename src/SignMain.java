
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class SignMain {

	public static void main1(String[] args) {
		//设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的21:09:00执行，
        calendar.set(year, month, day, 0, 10, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        System.out.println(date);
        
        //int period = 2 * 1000;
        //每天的date时刻执行task，每隔2秒重复执行
        //timer.schedule(task, date, period);
        //每天的date时刻执行task, 仅执行一次
        SignTask task = new SignTask();
        timer.schedule(task, date);
	}
	

	//时间间隔  
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    
	public static void main(String[] args) {
		//第1次执行生成加密串 
		Thread thread = new Thread(new SignTask());
		thread.start();
		
		Calendar calendar = Calendar.getInstance();   
		System.out.println("年: " + calendar.get(Calendar.YEAR));
		System.out.println("月: " + (calendar.get(Calendar.MONTH) + 1) + "");
		System.out.println("日: " + calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println("时: " + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("分: " + calendar.get(Calendar.MINUTE));
		System.out.println("秒: " + calendar.get(Calendar.SECOND));
		
		System.out.println("系统开始时间：" + calendar.getTime()); 
		 
        /*** 定制每日00:00:00执行方法 ***/  
        calendar.set(Calendar.HOUR_OF_DAY, 0);  
        calendar.set(Calendar.MINUTE, 5);  
        calendar.set(Calendar.SECOND, 0);  
        
        Date date=calendar.getTime(); //第一次执行定时任务的时间  
       
        
        System.out.println("before 方法比较："+date.before(new Date()));  
        //如果第一次执行定时任务的时间 小于 当前的时间  
        //此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准  
        if (date.before(new Date())) {  
            date = addDay(date, 1);  
            System.out.println("明天" +date + "进行执行");  
        }  

        System.out.println(date);
        
        Timer timer = new Timer();  
           
        SignTask task = new SignTask();  
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。  
        timer.schedule(task,date,PERIOD_DAY);  
       }  

	// 增加或减少天数  
	public static Date  addDay(Date date, int num) {  
		 Calendar startDT = Calendar.getInstance();  
		 startDT.setTime(date);  
		 startDT.add(Calendar.DAY_OF_MONTH, num);  
		 return startDT.getTime();  
	}  
}
