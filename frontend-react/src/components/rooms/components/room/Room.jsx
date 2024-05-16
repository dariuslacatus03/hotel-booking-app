import { Card, CardContent } from "@mui/material";

export default function Room({room}){
    return (
        <Card variant="outlined" sx={{ width: 250, height: 250, display: "flex", flexDirection: "column", justifyContent: "center"}}>
            <CardContent sx={{gap: 10}}>
                <div>
                    Room number: {room.roomNumber} <br />
                    Room type: {room.type} <br />
                    Room price: {room.price}<br />                    
                </div>
            </CardContent>
        </Card>
    )   
}