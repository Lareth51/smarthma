package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class TimePeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    private BeginPosition beginPosition;
    private EndPosition endPosition;
    private String GmlId;
    private String Prefix;


    /**
     * @return The beginPosition
     */
    public BeginPosition getBeginPosition() {
        return beginPosition;
    }

    /**
     * @param beginPosition The beginPosition
     */
    public void setBeginPosition(BeginPosition beginPosition) {
        this.beginPosition = beginPosition;
    }

    /**
     * @return The endPosition
     */
    public EndPosition getEndPosition() {
        return endPosition;
    }

    /**
     * @param endPosition The endPosition
     */
    public void setEndPosition(EndPosition endPosition) {
        this.endPosition = endPosition;
    }

    /**
     * @return The GmlId
     */
    public String getGmlId() {
        return GmlId;
    }

    /**
     * @param GmlId The _gml:id
     */
    public void setGmlId(String GmlId) {
        this.GmlId = GmlId;
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
        return new HashCodeBuilder().append(beginPosition).append(endPosition)
                .append(GmlId).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TimePeriod)) {
            return false;
        }
        TimePeriod rhs = ((TimePeriod) other);
        return new EqualsBuilder().append(beginPosition, rhs.beginPosition)
                .append(endPosition, rhs.endPosition).append(GmlId, rhs.GmlId)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
