package time;

import org.junit.Test;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author yujiaqi
 * @date 2019-05-02 09:25
 * @description
 */
public class LocalDateTimeTest {

    /**
     * 1.LocalDate LocalTime LocalDateTime
     */
    @Test
    public void test01(){
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = LocalDateTime.of(2019, 5, 2, 8, 27, 10);
        System.out.println(localDateTime2);

        LocalDateTime localDateTime3 = localDateTime1.plusYears(2);
        System.out.println(localDateTime3);

        LocalDateTime localDateTime4 = localDateTime1.minusYears(5);
        System.out.println(localDateTime4);

        System.out.println(localDateTime1.getYear());
        System.out.println(localDateTime1.getMonthValue());
        System.out.println(localDateTime1.getDayOfMonth());
    }

    /**
     * 2.Instant:时间戳(以Unix 元年：1970年1月1号00:00:00到某个时间之间的毫秒数)
     */
    @Test
    public void test02(){
        //默认获取UTC 时区
        Instant instant1 = Instant.now();
        System.out.println(instant1);

        OffsetDateTime offset = instant1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offset);

        System.out.println(instant1.toEpochMilli());
    }

    /**
     * 3.Duration:计算两个时间之间的"时间"间隔;
     */
    @Test
    public void test03() throws InterruptedException {
        Instant instant1 = Instant.now();
        Thread.sleep(1000L);
        Instant instant2 = Instant.now();
        Duration duration = Duration.between(instant1, instant2);
        System.out.println(duration.toMillis());
        System.out.println("-----------");
    }

    /**
     * 4.Period:计算两个日期的间隔
     * @throws InterruptedException
     */
    @Test
    public void test04() throws InterruptedException {
        LocalDate localDate1 = LocalDate.of(2017,1,1);
        Thread.sleep(1000);
        LocalDate localDate2 = LocalDate.now();
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());

    }

    /**
     *5. 时间校正器
     */
    @Test
    public void test05(){
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = localDateTime1.withDayOfMonth(10);
        System.out.println(localDateTime2);

        LocalDateTime localDateTime3 = localDateTime1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(localDateTime3);

        //自定义:下一个工作日
        LocalDateTime with = localDateTime1.with((day) -> {
            LocalDateTime localDateTime = (LocalDateTime) day;
            //当前
            DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return localDateTime.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return localDateTime.plusDays(2);
            } else {
                return localDateTime.plusDays(1);
            }
        });
        System.out.println(with);
    }

    /**
     * 6.DateTimeFormatter:格式化时间|日期
     */
    @Test
    public void test06(){
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter df1 = DateTimeFormatter.ISO_DATE;
        System.out.println(ldt.format(df1));
        System.out.println("----------------");

        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter df3 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formatTime = ldt.format(df2);
        System.out.println(formatTime);

        //LocalDateTime ldt2 = LocalDateTime.parse(formatTime, df2);
        //System.out.println(ldt2);
    }

    @Test
    public void test07(){
        LocalDate currentDate = LocalDate.now();
        // 本月月初日期
        LocalDate firstDayDate = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        // 本月末日期
        LocalDate lastDayDate = currentDate.with(TemporalAdjusters.lastDayOfMonth());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter df3 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        System.out.println("firstDayDate:"+firstDayDate.format(df));
        System.out.println("lastDayDate:"+lastDayDate.format(df));

        Period period = Period.between(firstDayDate, lastDayDate);
        System.out.println(period.getDays());


    }
    @Test
    public void test08(){
        Date date=new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println( localDate.format(df));
    }

    @Test
    public void test09(){
        LocalDate currentDate = LocalDate.now();
        // 上个月日期
        LocalDate preMonthDate = currentDate.minusMonths(1);
        System.out.println(preMonthDate);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM");
        System.out.println(preMonthDate.format(df));
    }

    @Test
    public void test10(){

        LocalDateTime localDate1 = LocalDateTime.parse("2018-12-09 22:14:09", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDate2 = LocalDateTime.parse("2018-12-09 22:00:07", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        //Period period=Period.between(localDate1,localDate2);
        //System.out.println(period.getDays());
        Duration duration = Duration.between(localDate1, localDate2);

        System.out.println(duration.toDays());// 同一天
        System.out.println(duration.toMinutes());
        System.out.println(duration.toMillis());//相差毫秒数

    }

    @Test
    public void test11(){
        LocalDate localDate = LocalDate.now();
        LocalDate preDay = localDate.minusDays(10);
        System.out.println(preDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    public void test12(){
        LocalDate localDate = LocalDate.now();
        LocalDate preMonth = localDate.minusMonths(2);
        System.out.println(preMonth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }



}
