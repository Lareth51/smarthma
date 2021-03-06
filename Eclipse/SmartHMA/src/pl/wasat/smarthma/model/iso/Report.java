package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.DQDomainConsistency DQDomainConsistency;
    private String Prefix;


    /**
     * @return The DQDomainConsistency
     */
    public pl.wasat.smarthma.model.iso.DQDomainConsistency getDQDomainConsistency() {
        return DQDomainConsistency;
    }

    /**
     * @param DQDomainConsistency The DQ_DomainConsistency
     */
    public void setDQDomainConsistency(
            pl.wasat.smarthma.model.iso.DQDomainConsistency DQDomainConsistency) {
        this.DQDomainConsistency = DQDomainConsistency;
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
        return new HashCodeBuilder().append(DQDomainConsistency).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Report)) {
            return false;
        }
        Report rhs = ((Report) other);
        return new EqualsBuilder()
                .append(DQDomainConsistency, rhs.DQDomainConsistency)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
