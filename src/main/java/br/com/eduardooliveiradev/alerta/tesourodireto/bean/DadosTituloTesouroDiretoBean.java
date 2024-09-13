package br.com.eduardooliveiradev.alerta.tesourodireto.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosTituloTesouroDiretoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cd;
	private String nm;
	private String featrs;
	private Date mtrtyDt;
	private BigDecimal minInvstmtAmt;
	private BigDecimal untrInvstmtVal;
	private String invstmtStbl;
	private boolean semiAnulIntrstInd;
	private String rcvgIncm;
	private BigDecimal anulInvstmtRate;
	private BigDecimal anulRedRate;
	private BigDecimal minRedQty;
	private BigDecimal untrRedVal;
	private BigDecimal minRedVal;
	private String isinCd;
	private Date convDt;

	public int getCd() {
		return cd;
	}

	public void setCd(int cd) {
		this.cd = cd;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getFeatrs() {
		return featrs;
	}

	public void setFeatrs(String featrs) {
		this.featrs = featrs;
	}

	public Date getMtrtyDt() {
		return mtrtyDt;
	}

	public void setMtrtyDt(Date mtrtyDt) {
		this.mtrtyDt = mtrtyDt;
	}

	public BigDecimal getMinInvstmtAmt() {
		return minInvstmtAmt;
	}

	public void setMinInvstmtAmt(BigDecimal minInvstmtAmt) {
		this.minInvstmtAmt = minInvstmtAmt;
	}

	public BigDecimal getUntrInvstmtVal() {
		return untrInvstmtVal;
	}

	public void setUntrInvstmtVal(BigDecimal untrInvstmtVal) {
		this.untrInvstmtVal = untrInvstmtVal;
	}

	public String getInvstmtStbl() {
		return invstmtStbl;
	}

	public void setInvstmtStbl(String invstmtStbl) {
		this.invstmtStbl = invstmtStbl;
	}

	public boolean isSemiAnulIntrstInd() {
		return semiAnulIntrstInd;
	}

	public void setSemiAnulIntrstInd(boolean semiAnulIntrstInd) {
		this.semiAnulIntrstInd = semiAnulIntrstInd;
	}

	public String getRcvgIncm() {
		return rcvgIncm;
	}

	public void setRcvgIncm(String rcvgIncm) {
		this.rcvgIncm = rcvgIncm;
	}

	public BigDecimal getAnulInvstmtRate() {
		return anulInvstmtRate;
	}

	public void setAnulInvstmtRate(BigDecimal anulInvstmtRate) {
		this.anulInvstmtRate = anulInvstmtRate;
	}

	public BigDecimal getAnulRedRate() {
		return anulRedRate;
	}

	public void setAnulRedRate(BigDecimal anulRedRate) {
		this.anulRedRate = anulRedRate;
	}

	public BigDecimal getMinRedQty() {
		return minRedQty;
	}

	public void setMinRedQty(BigDecimal minRedQty) {
		this.minRedQty = minRedQty;
	}

	public BigDecimal getUntrRedVal() {
		return untrRedVal;
	}

	public void setUntrRedVal(BigDecimal untrRedVal) {
		this.untrRedVal = untrRedVal;
	}

	public BigDecimal getMinRedVal() {
		return minRedVal;
	}

	public void setMinRedVal(BigDecimal minRedVal) {
		this.minRedVal = minRedVal;
	}

	public String getIsinCd() {
		return isinCd;
	}

	public void setIsinCd(String isinCd) {
		this.isinCd = isinCd;
	}

	public Date getConvDt() {
		return convDt;
	}

	public void setConvDt(Date convDt) {
		this.convDt = convDt;
	}

}
