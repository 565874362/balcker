import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title Test.java
 * @Package PACKAGE_NAME
 * @date 2018年12月17日 11:02:21
 */
public class Test {

	@org.junit.Test
	public void testI(){
		System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
		System.out.println(LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY)));
		System.out.println(LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY)));
		System.out.println(LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(0,DayOfWeek.WEDNESDAY)));
		System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
		System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
		System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
	}
}

