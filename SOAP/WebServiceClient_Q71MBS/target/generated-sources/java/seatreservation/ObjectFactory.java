
package seatreservation;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the seatreservation package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _ArrayOfSeat_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "ArrayOfSeat");
    private static final QName _Seat_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "Seat");
    private static final QName _CinemaException_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "CinemaException");
    private static final QName _SeatStatus_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "SeatStatus");
    private static final QName _Init_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "Init");
    private static final QName _InitResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "InitResponse");
    private static final QName _GetAllSeats_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "GetAllSeats");
    private static final QName _GetAllSeatsResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "GetAllSeatsResponse");
    private static final QName _GetSeatStatus_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "GetSeatStatus");
    private static final QName _GetSeatStatusResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "GetSeatStatusResponse");
    private static final QName _Lock_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "Lock");
    private static final QName _LockResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "LockResponse");
    private static final QName _Unlock_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "Unlock");
    private static final QName _UnlockResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "UnlockResponse");
    private static final QName _Reserve_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "Reserve");
    private static final QName _ReserveResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "ReserveResponse");
    private static final QName _Buy_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "Buy");
    private static final QName _BuyResponse_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "BuyResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: seatreservation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfSeat }
     * 
     * @return
     *     the new instance of {@link ArrayOfSeat }
     */
    public ArrayOfSeat createArrayOfSeat() {
        return new ArrayOfSeat();
    }

    /**
     * Create an instance of {@link Seat }
     * 
     * @return
     *     the new instance of {@link Seat }
     */
    public Seat createSeat() {
        return new Seat();
    }

    /**
     * Create an instance of {@link CinemaException }
     * 
     * @return
     *     the new instance of {@link CinemaException }
     */
    public CinemaException createCinemaException() {
        return new CinemaException();
    }

    /**
     * Create an instance of {@link Init }
     * 
     * @return
     *     the new instance of {@link Init }
     */
    public Init createInit() {
        return new Init();
    }

    /**
     * Create an instance of {@link InitResponse }
     * 
     * @return
     *     the new instance of {@link InitResponse }
     */
    public InitResponse createInitResponse() {
        return new InitResponse();
    }

    /**
     * Create an instance of {@link GetAllSeats }
     * 
     * @return
     *     the new instance of {@link GetAllSeats }
     */
    public GetAllSeats createGetAllSeats() {
        return new GetAllSeats();
    }

    /**
     * Create an instance of {@link GetAllSeatsResponse }
     * 
     * @return
     *     the new instance of {@link GetAllSeatsResponse }
     */
    public GetAllSeatsResponse createGetAllSeatsResponse() {
        return new GetAllSeatsResponse();
    }

    /**
     * Create an instance of {@link GetSeatStatus }
     * 
     * @return
     *     the new instance of {@link GetSeatStatus }
     */
    public GetSeatStatus createGetSeatStatus() {
        return new GetSeatStatus();
    }

    /**
     * Create an instance of {@link GetSeatStatusResponse }
     * 
     * @return
     *     the new instance of {@link GetSeatStatusResponse }
     */
    public GetSeatStatusResponse createGetSeatStatusResponse() {
        return new GetSeatStatusResponse();
    }

    /**
     * Create an instance of {@link Lock }
     * 
     * @return
     *     the new instance of {@link Lock }
     */
    public Lock createLock() {
        return new Lock();
    }

    /**
     * Create an instance of {@link LockResponse }
     * 
     * @return
     *     the new instance of {@link LockResponse }
     */
    public LockResponse createLockResponse() {
        return new LockResponse();
    }

    /**
     * Create an instance of {@link Unlock }
     * 
     * @return
     *     the new instance of {@link Unlock }
     */
    public Unlock createUnlock() {
        return new Unlock();
    }

    /**
     * Create an instance of {@link UnlockResponse }
     * 
     * @return
     *     the new instance of {@link UnlockResponse }
     */
    public UnlockResponse createUnlockResponse() {
        return new UnlockResponse();
    }

    /**
     * Create an instance of {@link Reserve }
     * 
     * @return
     *     the new instance of {@link Reserve }
     */
    public Reserve createReserve() {
        return new Reserve();
    }

    /**
     * Create an instance of {@link ReserveResponse }
     * 
     * @return
     *     the new instance of {@link ReserveResponse }
     */
    public ReserveResponse createReserveResponse() {
        return new ReserveResponse();
    }

    /**
     * Create an instance of {@link Buy }
     * 
     * @return
     *     the new instance of {@link Buy }
     */
    public Buy createBuy() {
        return new Buy();
    }

    /**
     * Create an instance of {@link BuyResponse }
     * 
     * @return
     *     the new instance of {@link BuyResponse }
     */
    public BuyResponse createBuyResponse() {
        return new BuyResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSeat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfSeat }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "ArrayOfSeat")
    public JAXBElement<ArrayOfSeat> createArrayOfSeat(ArrayOfSeat value) {
        return new JAXBElement<>(_ArrayOfSeat_QNAME, ArrayOfSeat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Seat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Seat }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "Seat")
    public JAXBElement<Seat> createSeat(Seat value) {
        return new JAXBElement<>(_Seat_QNAME, Seat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CinemaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CinemaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "CinemaException")
    public JAXBElement<CinemaException> createCinemaException(CinemaException value) {
        return new JAXBElement<>(_CinemaException_QNAME, CinemaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeatStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SeatStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "SeatStatus")
    public JAXBElement<SeatStatus> createSeatStatus(SeatStatus value) {
        return new JAXBElement<>(_SeatStatus_QNAME, SeatStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Init }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Init }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "Init")
    public JAXBElement<Init> createInit(Init value) {
        return new JAXBElement<>(_Init_QNAME, Init.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InitResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "InitResponse")
    public JAXBElement<InitResponse> createInitResponse(InitResponse value) {
        return new JAXBElement<>(_InitResponse_QNAME, InitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSeats }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllSeats }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "GetAllSeats")
    public JAXBElement<GetAllSeats> createGetAllSeats(GetAllSeats value) {
        return new JAXBElement<>(_GetAllSeats_QNAME, GetAllSeats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSeatsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAllSeatsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "GetAllSeatsResponse")
    public JAXBElement<GetAllSeatsResponse> createGetAllSeatsResponse(GetAllSeatsResponse value) {
        return new JAXBElement<>(_GetAllSeatsResponse_QNAME, GetAllSeatsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSeatStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetSeatStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "GetSeatStatus")
    public JAXBElement<GetSeatStatus> createGetSeatStatus(GetSeatStatus value) {
        return new JAXBElement<>(_GetSeatStatus_QNAME, GetSeatStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSeatStatusResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetSeatStatusResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "GetSeatStatusResponse")
    public JAXBElement<GetSeatStatusResponse> createGetSeatStatusResponse(GetSeatStatusResponse value) {
        return new JAXBElement<>(_GetSeatStatusResponse_QNAME, GetSeatStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Lock }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Lock }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "Lock")
    public JAXBElement<Lock> createLock(Lock value) {
        return new JAXBElement<>(_Lock_QNAME, Lock.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LockResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LockResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "LockResponse")
    public JAXBElement<LockResponse> createLockResponse(LockResponse value) {
        return new JAXBElement<>(_LockResponse_QNAME, LockResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Unlock }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Unlock }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "Unlock")
    public JAXBElement<Unlock> createUnlock(Unlock value) {
        return new JAXBElement<>(_Unlock_QNAME, Unlock.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnlockResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UnlockResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "UnlockResponse")
    public JAXBElement<UnlockResponse> createUnlockResponse(UnlockResponse value) {
        return new JAXBElement<>(_UnlockResponse_QNAME, UnlockResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Reserve }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Reserve }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "Reserve")
    public JAXBElement<Reserve> createReserve(Reserve value) {
        return new JAXBElement<>(_Reserve_QNAME, Reserve.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReserveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReserveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "ReserveResponse")
    public JAXBElement<ReserveResponse> createReserveResponse(ReserveResponse value) {
        return new JAXBElement<>(_ReserveResponse_QNAME, ReserveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Buy }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Buy }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "Buy")
    public JAXBElement<Buy> createBuy(Buy value) {
        return new JAXBElement<>(_Buy_QNAME, Buy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuyResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", name = "BuyResponse")
    public JAXBElement<BuyResponse> createBuyResponse(BuyResponse value) {
        return new JAXBElement<>(_BuyResponse_QNAME, BuyResponse.class, null, value);
    }

}
