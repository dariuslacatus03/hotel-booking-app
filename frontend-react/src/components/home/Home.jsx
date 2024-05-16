import Hotels from "../hotels/Hotels";
import Content from "../content/Content";
import "./Home.css"
import { useState } from "react";

export default function Home({ userLocation, hotelList, setHotelList }) {
    const [selectedHotel, setSelectedHotel] = useState(null);
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [roomsOfHotel, setRoomsOfHotel] = useState(null);

    return (
        <>
            <Hotels 
                hotelList = {hotelList} 
                setHotelList={setHotelList}
                setSelectedHotel={setSelectedHotel}
                userLocation={userLocation}
                setRoomsOfHotel={setRoomsOfHotel}
                setSelectedRoom={setSelectedRoom}
                />
            <Content 
                selectedHotel={selectedHotel}
                roomsOfHotel={roomsOfHotel}
                setRoomsOfHotel={setRoomsOfHotel}
                selectedRoom={selectedRoom}
                setSelectedRoom={setSelectedRoom}/>
        </>
    );
  }
  