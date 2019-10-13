package dto;

import java.sql.Timestamp;

public class HealthCheckDto {

	private String ServerStatus;
	private Timestamp Time;
	private String DataCenter;
	private String Url;

	public String getServerStatus() {
		return ServerStatus;
	}
	public void setServerStatus(String serverStatus) {
		ServerStatus = serverStatus;
	}
	public Timestamp getTime() {
		return Time;
	}
	public void setTime(Timestamp time) {
		Time = time;
	}
	public String getDataCenter() {
		return DataCenter;
	}
	public void setDataCenter(String dataCenter) {
		DataCenter = dataCenter;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
	
	
}
