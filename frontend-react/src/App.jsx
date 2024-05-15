import React, { useState, useEffect } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Home from './components/home/Home';
import HotelService from './service/HotelService';

function App() {
  const [userLocation, setUserLocation] = useState(null);
  const [hotelList, setHotelList] = useState([]);

  useEffect(() => {
        HotelService.getAllHotels().then((data) => {
          console.log(data);
          setHotelList(data);
        }).catch((error) => {
          console.log(error);
        });
}, [])

  useEffect(() => {
    const getUserLocation = () => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            const { latitude, longitude } = position.coords;
            setUserLocation({ latitude, longitude });
          },
          (error) => {
            console.error('Error getting user location:', error);
          }
        );
      }
      else {
        console.error('Geolocation is not supported by this browser.');
      }
    };

    getUserLocation();
  }, []);

  return (
    <div>
        <Router>
            <Routes>
                <Route path='/' element={ 
                <Home 
                  userLocation = {userLocation}
                  hotelList = {hotelList}  
                /> }/>
            </Routes>
        </Router>
    </div>
);
}

export default App;