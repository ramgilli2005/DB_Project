package com.ots.dao;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ots.beans.AggregateInfo;
import com.ots.util.MySqlExecute;

import java.sql.Timestamp;
import java.util.ArrayList;
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
		List<AggregateInfo> aggrInfo = new ArrayList<AggregateInfo>();
		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while (rs.next()) {
				AggregateInfo aggregateInfo = new AggregateInfo();
				aggregateInfo.setAggregateValue(rs.getDouble(1));
				aggregateInfo.setStartDate(rs.getTimestamp(2));
				aggrInfo.add(aggregateInfo);
			}
			if (type == "DAILY") {
				long oneDay = 1 * 24 * 60 * 60 * 1000;
				List<AggregateInfo> aggreInfo = new ArrayList<AggregateInfo>();
				AggregateInfo dailyInfo = new AggregateInfo();
				Timestamp ts = startDate;
				Timestamp t = startDate;
				int i;
				boolean flag = true;
				for (ts = startDate, i = 1; ts != endDate; ts = aggrInfo.get(
						++i).getStartDate()) {
					if (flag == true) {
						t = ts;
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
					aggreInfo.add(dailyInfo);
					dailyInfo = new AggregateInfo();
					flag = true;
				}
				return aggreInfo;
			} else if (type == "WEEKLY") {
				// 84600000 milliseconds in a day
				long oneWeek = 7 * 24 * 60 * 60 * 1000;
				List<AggregateInfo> aggreInfo = new ArrayList<AggregateInfo>();
				AggregateInfo weeklyInfo = new AggregateInfo();
				Timestamp ts = startDate;
				Timestamp t = startDate;
				int i;
				boolean flag = true;
				for (ts = startDate, i = 1; ts != endDate; ts = aggrInfo.get(
						++i).getStartDate()) {
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
				return aggreInfo;
			} else if (type == "MONTHLY") {
				long oneMonth = 30 * 24 * 60 * 60 * 1000;
				List<AggregateInfo> aggreInfo = new ArrayList<AggregateInfo>();
				AggregateInfo MonthlyInfo = new AggregateInfo();
				Timestamp ts = startDate;
				Timestamp t = startDate;
				int i;
				boolean flag = true;
				for (ts = startDate, i = 1; ts != endDate; ts = aggrInfo.get(
						++i).getStartDate()) {
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
				return aggreInfo;
			}
		} catch (Exception e) {
			log.error("Error while fetching record on daily weekly and monthly basis"
					+ e);
		}
		return aggrInfo;
	}

}