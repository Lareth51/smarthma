package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class EXExtent implements Serializable {

    private static final long serialVersionUID = 1L;

    private TemporalElement temporalElement;
    private GeographicElement geographicElement;
    private String Prefix;


    /**
     * @return The temporalElement
     */
    public TemporalElement getTemporalElement() {
        return temporalElement;
    }

    /**
     * @param temporalElement The temporalElement
     */
    public void setTemporalElement(TemporalElement temporalElement) {
        this.temporalElement = temporalElement;
    }

    /**
     * @return The geographicElement
     */
    public GeographicElement getGeographicElement() {
        return geographicElement;
    }

    /**
     * @param geographicElement The geographicElement
     */
    public void setGeographicElement(GeographicElement geographicElement) {
        this.geographicElement = geographicElement;
    }

    /**
     * @return The Prefix
     */
    public String getPrefix() {
        return Prefix;
    }

    /**
     * @param Prefix The _prefix
     */
    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
    }

    @Override
    public String toString() {
        ToStringStyle style = new SmartHMAStringStyle();
        ToStringBuilder.setDefaultStyle(style);
        return ToStringBuilder.reflectionToString(this, style);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(temporalElement)
                .append(geographicElement).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EXExtent)) {
            return false;
        }
        EXExtent rhs = ((EXExtent) other);
        return new EqualsBuilder().append(temporalElement, rhs.temporalElement)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
