import AdminHeader from "../header/AdminHeader";
import AdminHotels from "./components/adminhotels/AdminHotels";
import AdminContent from "./components/admincontent/AdminContent";
import { useState } from "react";

export default function AdminPanel({hotelList, setHotelList}){
    const [selectedHotel, setSelectedHotel] = useState(null);
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [roomsOfHotel, setRoomsOfHotel] = useState(null);
    

    return(
        <>
            <AdminHeader />
            <AdminHotels 
                hotelList={hotelList}
                setHotelList={setHotelList}
                setSelectedHotel={setSelectedHotel}
                setSelectedRoom={setSelectedRoom}
                />
            <AdminContent 
                selectedHotel={selectedHotel}
                setSelectedHotel={setSelectedHotel}
                roomsOfHotel={roomsOfHotel}
                setRoomsOfHotel={setRoomsOfHotel}
                selectedRoom={selectedRoom}
                setSelectedRoom={setSelectedRoom}
                hotelList={hotelList}
                setHotelList={setHotelList}
                />
        </>
    )
}