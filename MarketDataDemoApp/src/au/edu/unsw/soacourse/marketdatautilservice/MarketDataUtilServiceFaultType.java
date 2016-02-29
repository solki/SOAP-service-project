
package au.edu.unsw.soacourse.marketdatautilservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MarketDataUtilServiceFaultType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MarketDataUtilServiceFaultType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="InvalidEventSetId"/&gt;
 *     &lt;enumeration value="InvalidTargetCode"/&gt;
 *     &lt;enumeration value="AlreadyBeingConvered"/&gt;
 *     &lt;enumeration value="ProgramError"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MarketDataUtilServiceFaultType")
@XmlEnum
public enum MarketDataUtilServiceFaultType {

    @XmlEnumValue("InvalidEventSetId")
    INVALID_EVENT_SET_ID("InvalidEventSetId"),
    @XmlEnumValue("InvalidTargetCode")
    INVALID_TARGET_CODE("InvalidTargetCode"),
    @XmlEnumValue("AlreadyBeingConvered")
    ALREADY_BEING_CONVERED("AlreadyBeingConvered"),
    @XmlEnumValue("ProgramError")
    PROGRAM_ERROR("ProgramError");
    private final String value;

    MarketDataUtilServiceFaultType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MarketDataUtilServiceFaultType fromValue(String v) {
        for (MarketDataUtilServiceFaultType c: MarketDataUtilServiceFaultType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
