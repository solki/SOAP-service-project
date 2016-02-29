
package au.edu.unsw.soacourse.soapcurrencyconvert;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExchangeRateFaultType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ExchangeRateFaultType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="InvalidBaseCode"/&gt;
 *     &lt;enumeration value="InvalidTargetCode"/&gt;
 *     &lt;enumeration value="ConnectionIOError"/&gt;
 *     &lt;enumeration value="NoValidResult"/&gt;
 *     &lt;enumeration value="ProgramError"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ExchangeRateFaultType")
@XmlEnum
public enum ExchangeRateFaultType {

    @XmlEnumValue("InvalidBaseCode")
    INVALID_BASE_CODE("InvalidBaseCode"),
    @XmlEnumValue("InvalidTargetCode")
    INVALID_TARGET_CODE("InvalidTargetCode"),
    @XmlEnumValue("ConnectionIOError")
    CONNECTION_IO_ERROR("ConnectionIOError"),
    @XmlEnumValue("NoValidResult")
    NO_VALID_RESULT("NoValidResult"),
    @XmlEnumValue("ProgramError")
    PROGRAM_ERROR("ProgramError");
    private final String value;

    ExchangeRateFaultType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ExchangeRateFaultType fromValue(String v) {
        for (ExchangeRateFaultType c: ExchangeRateFaultType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
