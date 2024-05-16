import { Button } from "@mui/material"
import ReservationService from "../../../../service/ReservationService";

export default function ActiveReservations({reservationsOfRoom, setReservationsOfRoom}){
    const handleReservationButton = (reservationId) => {
        ReservationService.deleteReservation(reservationId)
                .then(response => {
                    console.log(response.data)
                    const updatedReservations = reservationsOfRoom.filter(reservation => reservation.id !== reservationId);
                    setReservationsOfRoom(updatedReservations);
                })
                .catch(error => {
                    console.log("Error deleting reservation:", error);
                })
    }
    return (
        <div className="reservations-list-layout">
            <ul style={{listStyle:"none"}}>
                {reservationsOfRoom.map(reservation => (
                                <li data-testid={"list-item"} key={reservation.id}>
                                        <p>
                                            Reservation {reservation.id}: <br />
                                            Check in: {reservation.startDate} <br />
                                            Check out: {reservation.endDate} <br />
                                        </p>
                                        <Button onClick={() => handleReservationButton(reservation.id)}>Delete</Button>
                                </li>
                    ))}
            </ul>
        </div>
    )
}