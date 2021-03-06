package pl.wasat.smarthma.model.eo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class BrowseInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String __prefix;
	private Type type;
	private ReferenceSystemIdentifier referenceSystemIdentifier;
	private FileName fileName;
	private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String get__prefix() {
		return __prefix;
	}

	public void set__prefix(String __prefix) {
		this.__prefix = __prefix;
	}

	public BrowseInformation with__prefix(String __prefix) {
		this.__prefix = __prefix;
		return this;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public BrowseInformation withType(Type type) {
		this.type = type;
		return this;
	}

	public ReferenceSystemIdentifier getReferenceSystemIdentifier() {
		return referenceSystemIdentifier;
	}

	public void setReferenceSystemIdentifier(
			ReferenceSystemIdentifier referenceSystemIdentifier) {
		this.referenceSystemIdentifier = referenceSystemIdentifier;
	}

	public BrowseInformation withReferenceSystemIdentifier(
			ReferenceSystemIdentifier referenceSystemIdentifier) {
		this.referenceSystemIdentifier = referenceSystemIdentifier;
		return this;
	}

	public FileName getFileName() {
		return fileName;
	}

	public void setFileName(FileName fileName) {
		this.fileName = fileName;
	}

	public BrowseInformation withFileName(FileName fileName) {
		this.fileName = fileName;
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
