package pl.wasat.smarthma.model.iso;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class DescriptiveKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    private pl.wasat.smarthma.model.iso.MDKeywords MDKeywords;
    private String Prefix;


    /**
     * @return The MDKeywords
     */
    public pl.wasat.smarthma.model.iso.MDKeywords getMDKeywords() {
        return MDKeywords;
    }

    /**
     * @param MDKeywords The MD_Keywords
     */
    public void setMDKeywords(pl.wasat.smarthma.model.iso.MDKeywords MDKeywords) {
        this.MDKeywords = MDKeywords;
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
        return new HashCodeBuilder().append(MDKeywords).append(Prefix)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DescriptiveKeyword)) {
            return false;
        }
        DescriptiveKeyword rhs = ((DescriptiveKeyword) other);
        return new EqualsBuilder().append(MDKeywords, rhs.MDKeywords)
                .append(Prefix, rhs.Prefix)

                .isEquals();
    }

}
