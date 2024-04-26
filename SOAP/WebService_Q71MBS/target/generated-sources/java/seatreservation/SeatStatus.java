
package seatreservation;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeatStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="SeatStatus">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Free"/>
 *     <enumeration value="Locked"/>
 *     <enumeration value="Reserved"/>
 *     <enumeration value="Sold"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "SeatStatus")
@XmlEnum
public enum SeatStatus {

    @XmlEnumValue("Free")
    FREE("Free"),
    @XmlEnumValue("Locked")
    LOCKED("Locked"),
    @XmlEnumValue("Reserved")
    RESERVED("Reserved"),
    @XmlEnumValue("Sold")
    SOLD("Sold");
    private final String value;

    SeatStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SeatStatus fromValue(String v) {
        for (SeatStatus c: SeatStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
