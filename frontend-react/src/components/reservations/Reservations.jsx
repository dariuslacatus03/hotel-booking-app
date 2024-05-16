import "../home/Home.css";
import DatePickerValue from "./components/datepickervalue/DatePickerValue";
import ActiveReservations from "./components/activereservations/ActiveReservations";
import { useEffect, useState } from "react";
import ReservationService from "../../service/ReservationService";
import dayjs from "dayjs";
import { Button } from "@mui/material";

export default function Reservations({selectedRoom}){
    const [reservationsOfRoom, setReservationsOfRoom] = useState(null);
    const [checkIn, setCheckIn] = useState(dayjs().hour(10).minute(0).second(0));
    const [checkOut, setCheckOut] = useState(dayjs().hour(10).minute(0).second(0));

    useEffect(() => {
        if (selectedRoom) {
            ReservationService.getReservationsOfRoom(selectedRoom.id)
                .then(reservations => {
                    setReservationsOfRoom(reservations);
                })
                .catch(error => {
                    console.error("Error getting rooms:", error);
                });
        }
    }, [selectedRoom]);

    const handleReservationButton = () => {
        const reservation = {
            startDate: checkIn.format('YYYY-MM-DDTHH:mm:ss'),
            endDate: checkOut.format('YYYY-MM-DDTHH:mm:ss')
        };
        ReservationService.addReservationToRoom(selectedRoom.id, reservation)
                .then(newReservation => {
                    setReservationsOfRoom([...reservationsOfRoom, newReservation])
                })
                .catch(error => {
                    console.log("Error making reservation:", error);
                })
    };

    return (
        <div className="reservations-layout">
            Make a rezervation for room {selectedRoom.roomNumber} <br />
            <DatePickerValue checkIn={checkIn} setCheckIn={setCheckIn} checkOut={checkOut} setCheckOut={setCheckOut}/>
            <Button onClick={handleReservationButton}>Make reservation</Button>
            {reservationsOfRoom !== null && (
                <ActiveReservations reservationsOfRoom={reservationsOfRoom} setReservationsOfRoom={setReservationsOfRoom}/>
            )}
        </div>
    )
}