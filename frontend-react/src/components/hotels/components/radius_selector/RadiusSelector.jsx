import { useState } from 'react';
import { Button, ButtonBase, Slider, colors } from '@mui/material';
import '../../Hotels.css';
import HotelService from '../../../../service/HotelService';

export default function RadiusSelector({setHotelList, userLocation,setRoomsOfHotel}) {
    const [searchRadius, setSearchRadius] = useState(0);

    const handleSliderChange = (event, newValue) => {
        setSearchRadius(newValue);
    };

    const handleSearchClick = () => {
        HotelService.getHotelsInRadius(searchRadius, userLocation).then((data) => {
            setHotelList(data);
            setRoomsOfHotel(null);
        }).catch((error) => {
            console.log(error);
          });
    }

    return (
        <div className="radius-selector">
            <div>Select search radius:</div>
            <Slider
                value={searchRadius}
                onChange={handleSliderChange}
                aria-label="Default"
                valueLabelDisplay="auto"
            />
            <p>Selected Radius: {searchRadius}</p>
            <Button variant="outlined" onClick={handleSearchClick}>Search hotels in radius</Button>
        </div>
    );
}
