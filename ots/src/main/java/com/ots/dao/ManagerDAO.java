package com.ots.dao;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ots.beans.AggregateInfo;
import com.ots.util.MySqlExecute;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ManagerDAO {

	static final Logger log = Logger.getLogger(ManagerDAO.class);

	public List<AggregateInfo> CalculateAggregateInfo(Timestamp startDate,
			Timestamp endDate, String type) {
		String query = "select sum(txn_log_cost), txn_log_date,count(1) from transaction_log where txn_log_date between '"
				+ startDate
				+ "' and '"
				+ endDate
				+ "'"
				+ " and txn_log_status = 'APPROVED' group By txn_log_date order by txn_log_date";
		log.debug("Query in Aggregate: "+query);
		List<AggregateInfo> aggrInfo = new ArrayList<AggregateInfo>();
		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while (rs.next()) {
				AggregateInfo aggregateInfo = new AggregateInfo();
				aggregateInfo.setAggregateValue(rs.getDouble(1));
				aggregateInfo.setStartDate(rs.getTimestamp(2));
				aggrInfo.add(aggregateInfo);
			}
			if (type.equals("DAILY")) {
				long oneDay = 1 * 24 * 60 * 60 * 1000;
				List<AggregateInfo> aggreInfo = new ArrayList<AggregateInfo>();
				AggregateInfo dailyInfo = new AggregateInfo();
				Timestamp ts = startDate;
				Timestamp t = startDate;
				int i;
				boolean flag = true;
				log.debug("AggrInfo Size: "+aggrInfo.size());
				for (i = 0; i< aggrInfo.size(); i++) {
					ts = aggrInfo.get(i).getStartDate();
					if (flag == true) {
						t = ts;
						String str = ts.toString();
						str = str.substring(0, 10);
						str += " 00:00:00";
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						Date parsedDate = dateFormat.parse(str);
						t = new java.sql.Timestamp(parsedDate.getTime());
					}
					flag = false;
					if (ts.getTime() < (t.getTime() + oneDay)) {
						dailyInfo.setAggregateValue(dailyInfo
								.getAggregateValue()
								+ aggrInfo.get(i).getAggregateValue());
						dailyInfo.setCount(dailyInfo.getCount() + 1);
						continue;
					}
					dailyInfo.setStartDate(t);
					dailyInfo.setEndDate(ts);
					log.debug("ManagerDAO: End Date: "+ dailyInfo.getEndDate());
					log.debug("ManagerDAO: Count: "+ dailyInfo.getCount());
					aggreInfo.add(dailyInfo);
					dailyInfo = new AggregateInfo();
					flag = true;
				}
				dailyInfo.setStartDate(t);
				dailyInfo.setEndDate(ts);
				log.debug("ManagerDAO: End Date: "+ dailyInfo.getEndDate());
				log.debug("ManagerDAO: Count: "+ dailyInfo.getCount());
				aggreInfo.add(dailyInfo);
				return aggreInfo;
			} else if (type.equals("WEEKLY")) {
				// 84600000 milliseconds in a day
				long oneWeek = 7 * 24 * 60 * 60 * 1000;
				List<AggregateInfo> aggreInfo = new ArrayList<AggregateInfo>();
				AggregateInfo weeklyInfo = new AggregateInfo();
				Timestamp ts = startDate;
				Timestamp t = startDate;
				int i;
				boolean flag = true;
				for (i = 0; i< aggrInfo.size(); i++) {
					ts = aggrInfo.get(i).getStartDate();
					if (flag == true) {
						t = ts;
					}
					flag = false;
					if (ts.getTime() < (t.getTime() + oneWeek)) {
						weeklyInfo.setAggregateValue(weeklyInfo
								.getAggregateValue()
								+ aggrInfo.get(i).getAggregateValue());
						weeklyInfo.setCount(weeklyInfo.getCount() + 1);
						continue;
					}
					weeklyInfo.setStartDate(t);
					weeklyInfo.setEndDate(ts);
					aggreInfo.add(weeklyInfo);
					weeklyInfo = new AggregateInfo();
					flag = true;
				}
				weeklyInfo.setStartDate(t);
				weeklyInfo.setEndDate(ts);
				aggreInfo.add(weeklyInfo);
				return aggreInfo;
			} else if (type.equals("MONTHLY")) {
				long oneMonth = 30L * 24L * 60L * 60L * 1000L;
				List<AggregateInfo> aggreInfo = new ArrayList<AggregateInfo>();
				AggregateInfo MonthlyInfo = new AggregateInfo();
				Timestamp ts = startDate;
				Timestamp t = startDate;
				int i;
				boolean flag = true;
				for (i = 0; i< aggrInfo.size(); i++) {
					ts = aggrInfo.get(i).getStartDate();
					if (flag == true) {
						t = ts;
					}
					flag = false;
					if (ts.getTime() < (t.getTime() + oneMonth)) {
						MonthlyInfo.setAggregateValue(MonthlyInfo
								.getAggregateValue()
								+ aggrInfo.get(i).getAggregateValue());
						MonthlyInfo.setCount(MonthlyInfo.getCount() + 1);
						continue;
					}
					MonthlyInfo.setStartDate(t);
					MonthlyInfo.setEndDate(ts);
					aggreInfo.add(MonthlyInfo);
					MonthlyInfo = new AggregateInfo();
					flag = true;
				}
				MonthlyInfo.setStartDate(t);
				MonthlyInfo.setEndDate(ts);
				aggreInfo.add(MonthlyInfo);
				return aggreInfo;
			}
		} catch (Exception e) {
			log.error("Error while fetching record on daily weekly and monthly basis "
					+ e);
		}
		return aggrInfo;
	}

}