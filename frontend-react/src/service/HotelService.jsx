import axios from "axios";

class HotelService{
    async getAllHotels(){
        try {
            const response = await axios.get('http://localhost:8080/api/hotels/all');
            return response.data;
        } catch (error) {
            console.log(error);
        }
    }

    async getHotelsInRadius(radius, currentLocation) {
        try {
            console.log(JSON.stringify(currentLocation))
            const response = await axios.get('http://localhost:8080/api/hotels/radius', {
                params: {
                    "radius": radius,
                    "latitude": currentLocation.latitude,
                    "longitude": currentLocation.longitude
                }
            });
            return response.data;
        } catch (error) {
            console.log(error);
        }
    }

    async addHotel(hotel) {
        try{
            const response = await axios.post('http://localhost:8080/api/hotels/add-hotel', hotel);
            return response.data;
        } catch (error) {
            console.log(error)
        }
    }

    async addRoomToHotel(hotelId, room){
        try {
            const response = await axios.post('http://localhost:8080/api/hotels/' + hotelId +'/add-room', room);
            return response.data;
        } catch (error) {
            throw error
        }
    }

    async addReviewToHotel(hotelId, review){
        try {
            const response = await axios.post('http://localhost:8080/api/hotels/' + hotelId +'/add-review', review);
            return response.data;
        } catch (error) {
            throw error;
        }
    }

    async deleteHotel(hotelId) {
        try {
            const response = await axios.delete('http://localhost:8080/api/hotels/' + hotelId + '/delete');
            return response.data
        } catch (error) {
            throw error;
        }
    }

}
export default new HotelService