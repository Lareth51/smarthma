package pl.wasat.smarthma.model.eo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class DownlinkInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String __prefix;
	private AcquisitionStation acquisitionStation;
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String get__prefix() {
		return __prefix;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public DownlinkInformation with__prefix(String __prefix) {
		this.__prefix = __prefix;
		return this;
	}

	public AcquisitionStation getAcquisitionStation() {
		return acquisitionStation;
	}

	public void setAcquisitionStation(AcquisitionStation acquisitionStation) {
		this.acquisitionStation = acquisitionStation;
	}

	public DownlinkInformation withAcquisitionStation(
			AcquisitionStation acquisitionStation) {
		this.acquisitionStation = acquisitionStation;
		return this;
	}

	@Override
	public String toString() {
		ToStringStyle style = new SmartHMAStringStyle();
		ToStringBuilder.setDefaultStyle(style);
		return ToStringBuilder.reflectionToString(this, style);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
