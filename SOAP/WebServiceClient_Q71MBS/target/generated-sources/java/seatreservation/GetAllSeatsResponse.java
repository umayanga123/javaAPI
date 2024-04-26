
package seatreservation;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAllSeatsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="GetAllSeatsResponse">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="GetAllSeatsResult" type="{http://www.iit.bme.hu/soi/hw/SeatReservation}ArrayOfSeat"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllSeatsResponse", propOrder = {
    "getAllSeatsResult"
})
public class GetAllSeatsResponse {

    @XmlElement(name = "GetAllSeatsResult", required = true, nillable = true)
    protected ArrayOfSeat getAllSeatsResult;

    /**
     * Gets the value of the getAllSeatsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSeat }
     *     
     */
    public ArrayOfSeat getGetAllSeatsResult() {
        return getAllSeatsResult;
    }

    /**
     * Sets the value of the getAllSeatsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSeat }
     *     
     */
    public void setGetAllSeatsResult(ArrayOfSeat value) {
        this.getAllSeatsResult = value;
    }

}
