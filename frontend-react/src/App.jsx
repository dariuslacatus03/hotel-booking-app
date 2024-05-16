import React, { useState, useEffect } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Home from './components/home/Home';
import HotelService from './service/HotelService';
import Header from './components/header/Header';
import "./App.css";

function App() {
  const [userLocation, setUserLocation] = useState(null);
  const [hotelList, setHotelList] = useState([]);

  useEffect(() => {
        HotelService.getAllHotels()
          .then(hotels => {
            setHotelList(hotels);
          })
          .catch(error => {
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
    <div className='main-layout'>
        <Header />
        <Router>
            <Routes>
                <Route path='/' element={
                <Home 
                  userLocation = {userLocation}
                  hotelList = {hotelList}
                  setHotelList={setHotelList}
                /> }/>
            </Routes>
        </Router>
    </div>
);
}

export default App;