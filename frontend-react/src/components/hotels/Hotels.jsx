import Hotel from "./components/Hotel";
import { Link } from "react-router-dom";

export default function Hotels({hotelList}){
    
    return (
        <div>
            <ul style={{listStyle:"none"}}>
                    {hotelList.map(hotel => (
                                <li data-testid={"list-item"} key={hotel.id}>
                                    <Link to={`/hotels/${hotel.id}`} style={{display:"block"}}>
                                        <Hotel hotel = {hotel}/>
                                    </Link>
                                    <hr/>
                                </li>
                    ))}
            </ul>

        </div>
    )
}