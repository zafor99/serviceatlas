package com.bn.qa.webservice.restclient.handler;

public class Asset {
	private byte[] data;
	private long totalSize;
	private long startByte;
	private long endByte;
	private String fileName;
	
	public Asset() {}
	
	public Asset(byte[] data, long totalSize, long startByte, long endByte, String fileName) {
		this.data = data;
		this.totalSize = totalSize;
		this.startByte = startByte;
		this.endByte = endByte;
		this.fileName = fileName;
	}
	
	/**
	 * Get the binary data as a byte array
	 * @return
	 */
	public byte[] getData() { return this.data; }
	
	/**
	 * Get the total size of the entire content (greater than or equal to contained packet size)
	 * @return
	 */
	public long getTotalSize() { return this.totalSize; }
	
	/**
	 * Return the start index of the byte array in the entire content range
	 * @return
	 */
	public long getStartByte() { return this.startByte; }
	
	/**
	 * Return the end index of the byte array in the entire content range
	 * @return
	 */
	public long getEndByte() { return this.endByte; }
	
	/**
	 * Return file name
	 * @return
	 */
	public String getFileName() { return this.fileName; }
	
	protected void setData(byte[] data) { this.data = data; }
	protected void setTotalSize(long totalSize) { this.totalSize = totalSize; }
	protected void setStartByte(long startByte) { this.startByte = startByte; }
	protected void setEndByte(long endByte) { this.endByte = endByte; }
	protected void setFileName(String fileName) { this.fileName = fileName; }
}

