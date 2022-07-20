package com.example.exceldemo;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class ExcelDemoApplicationTests {

	@Test
	void contextLoads() {
	}

//		（"2020-06-25 ","2020-07-01"],
//		"2020-07-01 ","2020-07-09",
//		"2020-07-09","2020-07-10",

	public static void main(String[] args) {
		String startTime = "2020-07-10";
		String endTime = "2020-07-20";
		String [] testCase = new String[] {
				"2020-07-01","2020-07-09",
				"2020-07-01","2020-07-10",
				"2020-07-01","2020-07-11",
				"2020-07-10","2020-07-15",
				"2020-07-10","2020-07-20",
				"2020-07-15","2020-07-21",
				"2020-07-20","2020-07-23",
				"2020-07-21","2020-07-22",
				"2020-07-01","2020-07-30"






		};
		for (int i = 0; i < testCase.length; i+=2) {
			boolean matched = match(startTime, endTime, testCase[i], testCase[i + 1]);
			System.out.println("重复申报:"+matched);
		}
	}



	static boolean match(String projectStartTime1, String projectEndTime1,String projectStartTime2,String projectEndTime2)
	{
		LocalDate startTime1 = formatDate(projectStartTime1);
		LocalDate endTime1 = formatDate(projectEndTime1);
		LocalDate startTime2 =formatDate(projectStartTime2);
		LocalDate endTime2 = formatDate(projectEndTime2);
		return !(startTime2.isAfter(endTime1)||startTime1.isAfter(endTime2));
	}

	public static LocalDate formatDate(String date) {
		return LocalDate.parse(date);
	}




	@Test
	public void vvv() throws ParseException {
		java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
		String s= "2011-07-09 ";
		Date date =  formatter.parse(s);
	}




	@Test
	public void test() throws ParseException {
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd " );

//		Date startDate1 = sdf.parse("2020-07-10 ");
//		Date endDate1 = sdf.parse("2020-07-15 ");
//		Date startDate2 = sdf.parse("2020-07-15 ");
//		Date endDate2 = sdf.parse("2020-07-20 ");
//		Boolean flag = isOverlap(startDate1.getTime(),endDate1.getTime(),startDate2.getTime(),endDate2.getTime(),false);
//		System.out.println("------------"+flag);


		Date startDate1 = sdf.parse("2020-07-10 ");
		Date endDate1 = sdf.parse("2020-07-15 ");
		Date startDate2 = sdf.parse("2020-07-08 ");
		Date endDate2 = sdf.parse("2020-07-20 ");
		Boolean flag = isOverlap(startDate1.getTime(),endDate1.getTime(),startDate2.getTime(),endDate2.getTime(),false);
		System.out.println("------------"+flag);
	}


	/**
	 * 判断2个时间段是否有重叠（交集）
	 * @param startDate1 时间段1开始时间戳
	 * @param endDate1 时间段1结束时间戳
	 * @param startDate2 时间段2开始时间戳
	 * @param endDate2 时间段2结束时间戳
	 * @param isStrict 是否严格重叠，true 严格，没有任何相交或相等；false 不严格，可以首尾相等，比如2021/5/29-2021/5/31和2021/5/31-2021/6/1，不重叠。
	 * @return 返回是否重叠
	 */
	public static boolean isOverlap(long startDate1, long endDate1, long startDate2, long endDate2, boolean isStrict){
		if(isStrict){
			if(! (endDate1<startDate2 || startDate1>endDate2)){
				return true;
			}
		}else{
			if(! (endDate1<=startDate2 || startDate1>=endDate2)){
				return true;
			}
		}
		return false;
	}


	@Test
	public  void test1() {
		List<User> userList = new ArrayList<>();
		userList.add(new User(1L,"1111","张三"));
		userList.add(new User(1L,"2222","张三"));
		userList.add(new User(3L,"3333","王五"));
		userList.add(new User(4L,"4444","赵六"));

		System.out.println("转换前--->> " + JSON.toJSONString(userList));
		//转list
//		List<String> userNameList = userList.stream().map(User::getUserName).collect(Collectors.toList());
		//转Set
//		Set<String> userNameSet = userList.stream().map(User::getUserName).collect(Collectors.toSet());
		//转Map
		Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, u -> u));

//		System.out.println("转换后List--->> " + JSON.toJSONString(userNameList));
//		System.out.println("转换后Set--->> " + JSON.toJSONString(userNameSet));
		System.out.println("转换后Map--->> " + JSON.toJSONString(userMap));
	}

	@Data
	@AllArgsConstructor
	class User implements Serializable {

		private Long id;

		private String userNo;

		private String userName;
	}





	}
