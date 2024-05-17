import axios from "axios";

class RoomService{
    
    async getRoomsOfHotel(hotelId){
        try{
            const response = await axios.get('http://localhost:8080/api/rooms/'+ hotelId + '/get');
            return response.data;
        } catch (error) {
            console.log(error);
        }
    }

    async addReservationToRoom(roomId, reservation){
        try {
            const response = await axios.post('http://localhost:8080/api/rooms/' + roomId + '/add-reservation', reservation);
            return response.data;
        } catch (error) {
            throw error
        }
    }

    async deleteRoom(roomId) {
        try {
            const response = await axios.delete('http://localhost:8080/api/rooms/' + roomId + '/delete');
            return response.data
        } catch (error) {
            throw error;
        }
    }


}
export default new RoomService