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

    async getRoomsOfHotel(hotelId){
        try{
            const response = await axios.get('http://localhost:8080/api/hotels/'+ hotelId + '/get');
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

}
export default new HotelService