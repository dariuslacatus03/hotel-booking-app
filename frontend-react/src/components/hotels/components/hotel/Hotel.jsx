import {Card, CardContent} from "@mui/material";
import { Divider } from "@mui/material";
import"../../Hotels.css"

export default function Hotel({hotel}){
    return (
        <Card variant="outlined" sx={{ width: 250, height: 250, display: "flex", flexDirection: "column"}}>
            <CardContent sx={{gap: 10}}>
                <h2>
                    {hotel.name}
                    <Divider />
                </h2>
            </CardContent>
            <div className="total-rooms">Total rooms: {hotel.numberOfRooms}</div>
        </Card>
    )
}