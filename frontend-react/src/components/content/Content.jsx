import { useEffect, useState } from "react";
import "../home/Home.css";
import HotelService from "../../service/HotelService";
import Rooms from "../rooms/Rooms";
import { Box, } from "@mui/material";
import Reservations from "../reservations/Reservations";

export default function Content({ selectedHotel, roomsOfHotel, setRoomsOfHotel, selectedRoom, setSelectedRoom}) {

    useEffect(() => {
        if (selectedHotel) {
            HotelService.getRoomsOfHotel(selectedHotel.id)
                .then(rooms => {
                    setRoomsOfHotel(rooms);
                })
                .catch(error => {
                    console.error("Error fetching rooms:", error);
                });
        }
    }, [selectedHotel]);
    
    return (
        <div className="content-layout">
            <Box component="section" sx={{border: "1px solid rgba(0, 0, 0, 0.12)", 
                                            borderRadius: "5px",
                                            height: "600px", 
                                            width: "-webkit-fill-available", 
                                            overflow: 'overlay', 
                                            position: "fixed",
                                            display: "flex"}}>
                {selectedHotel && roomsOfHotel !== null && (
                    <>
                    <Rooms roomsOfHotel={roomsOfHotel} setSelectedRoom={setSelectedRoom}/>                
                    {selectedRoom !== null && (
                       <Reservations selectedRoom={selectedRoom}/>
                    )}
                    {selectedRoom === null &&(
                        <div style={{padding: "10px"}}>Select a room from the list provided to make a reservation</div>
                    )}
                    </>
                )}
                {!selectedHotel && (
                    <div style={{padding: "10px"}}>
                        Select a hotel from the list provided to see the rooms available for you
                    </div>
                )}
            </Box>
        </div>
    );
}