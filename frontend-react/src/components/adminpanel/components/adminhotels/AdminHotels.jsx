import { Button } from "@mui/material";
import AdminHotel from "../adminhotel/AdminHotel";
import "../../AdminPanel.css"
export default function AdminHotels({hotelList, setHotelList, setSelectedHotel, setSelectedRoom}){

    const handleHotelClick = (hotel) => {
        setSelectedHotel(hotel);
        setSelectedRoom(null);
    };
    
    return (
        <div className="admin-hotel-list-layout">
            <ul style={{listStyle:"none"}}>
                    {hotelList.map(hotel => (
                                <li data-testid={"list-item"} key={hotel.id}>
                                    <Button style={{display:"block"}} onClick={() => handleHotelClick(hotel)}>
                                        <AdminHotel hotel = {hotel}/>
                                    </Button>
                                </li>
                    ))}
            </ul>

        </div>
    )
}