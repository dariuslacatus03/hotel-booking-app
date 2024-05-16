import { Button } from "@mui/material"
import ReservationService from "../../../../service/ReservationService";
import { useState } from "react";

export default function ActiveReservations({reservationsOfRoom, setReservationsOfRoom}){
    const [deleteErrorMessage, setDeleteErrorMessage] = useState(null);
    
    const handleDeleteButton = (reservationId) => {
        ReservationService.deleteReservation(reservationId)
                .then(response => {
                    console.log(response.data)
                    const updatedReservations = reservationsOfRoom.filter(reservation => reservation.id !== reservationId);
                    setReservationsOfRoom(updatedReservations);
                    setDeleteErrorMessage(null);
                })
                .catch(error => {
                    console.log("Error deleting reservation:", error);
                    if (error.response && error.response.data) {
                        setDeleteErrorMessage(error.response.data);
                    }
                })
    }

    return (
        <div className="reservations-list-layout">
            {deleteErrorMessage !== null && (
                <p>{deleteErrorMessage}</p>
            )}
            <ul style={{listStyle:"none"}}>
                {reservationsOfRoom.map(reservation => (
                                <li data-testid={"list-item"} key={reservation.id}>
                                        <p>
                                            Reservation {reservation.id}: <br />
                                            Check in: {reservation.startDate} <br />
                                            Check out: {reservation.endDate} <br />
                                        </p>
                                        <Button onClick={() => handleDeleteButton(reservation.id)}>Delete</Button>
                                </li>
                    ))}
            </ul>
        </div>
    )
}