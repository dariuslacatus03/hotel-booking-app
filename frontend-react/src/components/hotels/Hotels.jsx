import Hotel from "./components/hotel/Hotel";
import RadiusSelector from "./components/radius_selector/RadiusSelector";
import "./Hotels.css"
import { Button } from "@mui/material";

export default function Hotels({hotelList, setHotelList, setSelectedHotel, userLocation, setRoomsOfHotel, setSelectedRoom}){

    const handleHotelClick = (hotel) => {
        setSelectedHotel(hotel);
        setSelectedRoom(null);
    };
    
    return (
        <div className="hotel-list-layout">
            <RadiusSelector setHotelList={setHotelList} userLocation={userLocation} setRoomsOfHotel={setRoomsOfHotel}/>
            <ul style={{listStyle:"none", justifyContent:"center"}}>
                    {hotelList.map(hotel => (
                                <li data-testid={"list-item"} key={hotel.id}>
                                    <Button style={{display:"block"}} onClick={() => handleHotelClick(hotel)}>
                                        <Hotel hotel = {hotel}/>
                                    </Button>
                                </li>
                    ))}
            </ul>

        </div>
    )
}