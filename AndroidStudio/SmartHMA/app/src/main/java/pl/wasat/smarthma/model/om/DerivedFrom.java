package pl.wasat.smarthma.model.om;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import pl.wasat.smarthma.utils.text.SmartHMAStringStyle;

public class DerivedFrom implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _prefix;
    private String _xlink_href;


    public String get_prefix() {
        return _prefix;
    }

    public void set_prefix(String _prefix) {
        this._prefix = _prefix;
    }

    public DerivedFrom with_prefix(String _prefix) {
        this._prefix = _prefix;
        return this;
    }

    public String get_xlink_href() {
        return _xlink_href;
    }

    public void set_xlink_href(String _xlink_href) {
        this._xlink_href = _xlink_href;
    }

    public DerivedFrom with_xlink_href(String _xlink_href) {
        this._xlink_href = _xlink_href;
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


}
