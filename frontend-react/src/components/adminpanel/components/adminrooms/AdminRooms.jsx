import { Button } from "@mui/material";
import AdminRoom from "../adminroom/AdminRoom";
import HotelService from "../../../../service/HotelService";
import "../../AdminPanel.css"

export default function AdminRooms({hotelList, setHotelList, selectedHotel, setSelectedHotel, roomsOfHotel, setSelectedRoom}){
    const handleRoomClick = (room) => {
        setSelectedRoom(room);
    };
    console.log(roomsOfHotel);

    const handleBackButton = () => {
        setSelectedHotel(null);
    }

    const handleDeleteButton = (hotelId) => {
        HotelService.deleteHotel(hotelId)
            .then(response => {
                console.log(response.data)
                const updatedHotels = hotelList.filter(hotel => hotel.id !== hotelId);
                setHotelList(updatedHotels);
                setSelectedHotel(null);
            })
            .catch(error => {
                console.log(error);
            })
    }

    return (
        <div className="admin-rooms-list-layout">
            <div style={{padding:"10px"}}>
                <Button onClick={handleBackButton}>Back</Button> <br /><br />
                {selectedHotel.name}<br />
                <Button sx={{color: "red"}} onClick={() => handleDeleteButton(selectedHotel.id)}>Delete hotel</Button>
            </div>
            <ul style={{listStyle:"none", display: "ruby"}}>
                {roomsOfHotel.map(room => (
                                <li data-testid={"list-item"} key={room.id} style={{width:"50%"}}>
                                    <Button style={{display:"block"}} onClick={() => handleRoomClick(room)}>
                                        <AdminRoom room = {room}/>
                                    </Button>
                                </li>
                    ))}
            </ul>
        </div>
    )
}