package com.jci.model;

public class PoDetails {

	private Long poId;
	private int poNum;
	private String poDesc;
	private int dataSource;
	private int status;
	public Long getPoId() {
		return poId;
	}
	public void setPoId(Long poId) {
		this.poId = poId;
	}
	public int getPoNum() {
		return poNum;
	}
	public void setPoNum(int poNum) {
		this.poNum = poNum;
	}
	public String getPoDesc() {
		return poDesc;
	}
	public void setPoDesc(String poDesc) {
		this.poDesc = poDesc;
	}
	public int getDataSource() {
		return dataSource;
	}
	public void setDataSource(int dataSource) {
		this.dataSource = dataSource;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dataSource;
		result = prime * result + ((poId == null) ? 0 : poId.hashCode());
		result = prime * result + poNum;
		result = prime * result + status;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PoDetails other = (PoDetails) obj;
		if (dataSource != other.dataSource)
			return false;
		if (poId == null) {
			if (other.poId != null)
				return false;
		} else if (!poId.equals(other.poId))
			return false;
		if (poNum != other.poNum)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PoDetails [poId=" + poId + ", poNum=" + poNum + ", poDesc=" + poDesc + ", dataSource=" + dataSource
				+ ", status=" + status + "]";
	}
	
	
}
