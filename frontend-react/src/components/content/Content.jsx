import { useEffect, useState } from "react";
import "../home/Home.css";
import HotelService from "../../service/HotelService";
import Rooms from "../rooms/Rooms";
import { Box, Button, Rating, TextField, ratingClasses, } from "@mui/material";
import Reservations from "../reservations/Reservations";
import ReviewService from "../../service/ReviewService";
import Reviews from "../reviews/Reviews";

function calculateAverageRating(reviews) {
    if (!reviews || reviews.length === 0) {
        return 0;
    }
    const ratingSum = reviews.reduce((sum, review) => sum + review.rating, 0);
    const averageRating = ratingSum / reviews.length;

    return averageRating.toFixed(2);
}

export default function Content({ selectedHotel, roomsOfHotel, setRoomsOfHotel, selectedRoom, setSelectedRoom}) {
    
    const [reviewsOfHotel, setReviewsOfHotel] = useState(null)

    useEffect(() => {
        if (selectedHotel) {
            HotelService.getRoomsOfHotel(selectedHotel.id)
                .then(rooms => {
                    setRoomsOfHotel(rooms);
                })
                .catch(error => {
                    console.error("Error fetching rooms:", error);
                });

            ReviewService.getReviewOfHotel(selectedHotel.id)
                .then(reviews => {
                    console.log(reviews)
                    setReviewsOfHotel(reviews);
                })
                .catch(error => {
                    console.error("Error fetching rooms:", error);
                });
        }
    }, [selectedHotel]);

    const [rating, setRating] = useState(5);
    const [review, setReview] = useState('');

    const handleRatingChange = (event, newRating) => {
        setRating(newRating);
    };

    const handleReviewChange = (event) => {
        setReview(event.target.value);
    };


    const handleSendReviewButton = () => {
        const reviewToAdd = {
            reviewText: review,
            rating: rating
        }
        ReviewService.addReviewToHotel(selectedHotel.id, reviewToAdd)
                .then(newReview => {
                    setReviewsOfHotel([...reviewsOfHotel, newReview]);
                    setReview('');
                })
                .catch(error => {
                    console.log("Error sending review", error);
                })
    }

    return (
        <div className="content-layout">
            <Box component="section" sx={{border: "1px solid rgba(0, 0, 0, 0.12)", 
                                            borderRadius: "5px",
                                            height: "600px", 
                                            width: "-webkit-fill-available", 
                                            overflow: 'overlay', 
                                            position: "fixed",
                                            display: "flex"}}>
                {selectedHotel && roomsOfHotel !== null && (
                    <>
                    <Rooms roomsOfHotel={roomsOfHotel} setSelectedRoom={setSelectedRoom}/>                
                    {selectedRoom !== null && (
                       <Reservations selectedRoom={selectedRoom}/>
                    )}
                    {selectedRoom === null &&(
                        <div style={{padding: "10px"}}>
                        <div>
                            Select a room from the list provided to make a reservation, <br />
                            or leave a review to {selectedHotel.name} hotel
                        </div>
                        <TextField
                            id="outlined-multiline-static"
                            multiline
                            label={"Review"}
                            rows={4}
                            value={review}
                            onChange={handleReviewChange}
                            sx={{marginTop: "5px"}}
                            />
                        <br />
                        <p>Give Rating:</p>
                        <Rating
                            name="simple-controlled"
                            value={rating}
                            onChange={handleRatingChange}
                            />
                        <p>Your rating: {rating}/5</p>
                        <Button onClick={handleSendReviewButton}>Send review</Button>
                        <p>
                            {selectedHotel.name} average rating: {calculateAverageRating(reviewsOfHotel)}/5 <br />
                            {selectedHotel.name} reviews: 
                        </p>
                        <Reviews reviewsOfHotel={reviewsOfHotel} />
                      </div>
                    )}
                    </>
                )}
                {!selectedHotel && (
                    <div style={{padding: "10px"}}>
                        Select a hotel from the list provided to see the rooms available for you
                    </div>
                )}
            </Box>
        </div>
    );
}