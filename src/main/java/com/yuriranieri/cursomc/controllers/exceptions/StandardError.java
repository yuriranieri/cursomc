package com.yuriranieri.cursomc.controllers.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String msg;
	private LocalDateTime timeStap;

	public StandardError(Integer status, String msg, LocalDateTime timeStap) {
		this.status = status;
		this.msg = msg;
		this.timeStap = timeStap;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LocalDateTime getTimeStap() {
		return timeStap;
	}

	public void setTimeStap(LocalDateTime timeStap) {
		this.timeStap = timeStap;
	}

}
