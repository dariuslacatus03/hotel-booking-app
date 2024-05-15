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


}
export default new HotelService