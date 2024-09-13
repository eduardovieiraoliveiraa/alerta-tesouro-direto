package br.com.eduardooliveiradev.alerta.tesourodireto.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaTesouroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int responseStatus;
	private String responseStatusText;
	private String statusInfo;
	private ResponseTesouroDiretoBean response;

	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getResponseStatusText() {
		return responseStatusText;
	}

	public void setResponseStatusText(String responseStatusText) {
		this.responseStatusText = responseStatusText;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}

	public ResponseTesouroDiretoBean getResponse() {
		return response;
	}

	public void setResponse(ResponseTesouroDiretoBean response) {
		this.response = response;
	}

}
