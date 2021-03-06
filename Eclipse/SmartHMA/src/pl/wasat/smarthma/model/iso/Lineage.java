package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Lineage implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.LILineage LILineage;
    private String Prefix;


    /**
     * @return The LILineage
     */
    public pl.wasat.smarthma.model.iso.LILineage getLILineage() {
        return LILineage;
    }

    /**
     * @param LILineage The LI_Lineage
     */
    public void setLILineage(pl.wasat.smarthma.model.iso.LILineage LILineage) {
        this.LILineage = LILineage;
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
        return new HashCodeBuilder().append(LILineage).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Lineage)) {
            return false;
        }
        Lineage rhs = ((Lineage) other);
        return new EqualsBuilder().append(LILineage, rhs.LILineage)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
