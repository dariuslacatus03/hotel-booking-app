import { Button, Paper} from "@mui/material";
import Room from "./components/room/Room";
import "../home/Home.css"

export default function Rooms({roomsOfHotel, setSelectedRoom}){
    const handleRoomClick = (room) => {
        setSelectedRoom(room);
    };
    console.log(roomsOfHotel);

    return (
        <div className="rooms-list-layout">
            <ul style={{listStyle:"none"}}>
                {roomsOfHotel.map(room => (
                                <li data-testid={"list-item"} key={room.id} style={{width:"50%"}}>
                                    <Button style={{display:"block"}} onClick={() => handleRoomClick(room)}>
                                        <Room room = {room}/>
                                    </Button>
                                </li>
                    ))}
            </ul>
        </div>
    )
}