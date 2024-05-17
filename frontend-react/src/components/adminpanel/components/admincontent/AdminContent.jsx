import { useEffect, useState } from "react";
import HotelService from "../../../../service/HotelService";
import { Box, Button, TextField } from "@mui/material";
import AdminRooms from "../adminrooms/AdminRooms";
import LatitudeSlider from "./components/latitudeslider/LatitudeSlider";
import LongitudeSlider from "./components/longitudeslider/LongitudeSlider";
import RoomService from "../../../../service/RoomService";

export default function AdminContent({selectedHotel, setSelectedHotel, roomsOfHotel, setRoomsOfHotel, selectedRoom, setSelectedRoom, hotelList, setHotelList}){
    
    useEffect(() => {
        if (selectedHotel) {
            RoomService.getRoomsOfHotel(selectedHotel.id)
                .then(rooms => {
                    setRoomsOfHotel(rooms);
                })
                .catch(error => {
                    console.error("Error fetching rooms:", error);
                });
        }
    }, [selectedHotel]);

    const [latitude, setLatitude] = useState(0);
    const [longitude, setLongitude] = useState(0);
    const [hotelName, setHotelName] = useState('');

    const handleHotelNameChange = (event) => {
        setHotelName(event.target.value);
    };

    const handleAddHotelButton = () => {
        const hotelToAdd = {
            name: hotelName,
            latitude: latitude,
            longitude: longitude
        }
        HotelService.addHotel(hotelToAdd)
                .then(addedHotel => {
                    setHotelList([...hotelList, addedHotel]);
                })
                .catch(error => {
                    console.log(error);
                })
    };

    const [roomNumber, setRoomNumber] = useState(0); 
    const [roomType, setRoomType] = useState(0); 
    const [roomPrice, setRoomPrice] = useState(0); 

    const handleRoomNumberChange = (event) => {
        setRoomNumber(event.target.value)
    }
    const handleRoomTypeChange = (event) => {
        setRoomType(event.target.value)
    }
    const handleRoomPriceChange = (event) => {
        setRoomPrice(event.target.value)
    }

    const handleAddRoomButton = () => {
        const roomToAdd = {
            room_number: roomNumber,
            type: roomType,
            price: roomPrice
        }
        console.log(roomToAdd);
        HotelService.addRoomToHotel(selectedHotel.id, roomToAdd)
                .then(addedRoom => {
                    setRoomsOfHotel([...roomsOfHotel, addedRoom]);
                    setSelectedRoom(null);
                })
                .catch(error => {
                    console.log(error);
                })
    };

    const handleDeleteRoomButton = (roomId) => {
        RoomService.deleteRoom(roomId)
            .then(response => {
                console.log(response);
                const updatedRooms = roomsOfHotel.filter(room => room.id !== roomId);
                setRoomsOfHotel(updatedRooms);
                setSelectedRoom(null);
            })
            .catch(error => {
                console.log(error);
            })
    }
    
    return (
        <div className="admin-content-layout">
            <Box component="section" sx={{border: "1px solid rgba(0, 0, 0, 0.12)", 
                                            borderRadius: "5px",
                                            height: "600px", 
                                            width: "-webkit-fill-available", 
                                            overflow: 'overlay', 
                                            position: "fixed",
                                            display: "flex",
                                            gap: "5%"}}>
                {selectedHotel && roomsOfHotel !== null &&(
                    <>
                    <AdminRooms 
                        hotelList={hotelList}
                        setHotelList={setHotelList}
                        selectedHotel = {selectedHotel} 
                        setSelectedHotel={setSelectedHotel} 
                        roomsOfHotel={roomsOfHotel} 
                        setSelectedRoom={setSelectedRoom}
                        />                
                    {selectedRoom === null && (
                       <div style={{padding: "10px"}}>
                            Select a room from the list provided to make modifications <br />
                            or add a new room: <br /><br />
                            <TextField id="outlined-basic" type="number" label="Room number" variant="outlined" onChange={handleRoomNumberChange} /> <br /><br />
                            <TextField id="outlined-basic" type="number" label="Room type" variant="outlined" onChange={handleRoomTypeChange} /> <br /><br />
                            <TextField id="outlined-basic" type="number" label="Room price" variant="outlined" onChange={handleRoomPriceChange} /> <br /><br />
                            <Button onClick={handleAddRoomButton}>Add room</Button>
                       </div>
                    )}
                    {selectedRoom && (
                        <>
                            <div style={{padding: "10px"}}>
                                Room number {selectedRoom.roomNumber} selected<br />
                                Room type: {selectedRoom.type} <br />
                                Room price: {selectedRoom.price} <br />
                                <Button sx={{color: "red"}} onClick={() => handleDeleteRoomButton(selectedRoom.id)}>Delete room</Button>
                            </div>
                        </>
                    )}
                    </>
                )}
                {!selectedHotel && (
                    <>
                        <div style={{padding: "10px"}}>
                            Select a hotel from the list provided to make modifications <br />
                            or add a new hotel: <br /><br />
                            <TextField id="outlined-basic" label="Hotel Name" variant="outlined" onChange={handleHotelNameChange} /> <br /><br />
                            <LatitudeSlider latitude={latitude} setLatitude={setLatitude}/>
                            <LongitudeSlider longitude={longitude} setLongitude={setLongitude}/>
                            <Button onClick={handleAddHotelButton}>Add hotel</Button>
                        </div>
                    </>
                )}
            </Box>
        </div>
    )
}