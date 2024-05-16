import axios from "axios";

class ReservationService{
    
    async getReservationsOfRoom(roomId){
        try{
            const response = await axios.get('http://localhost:8080/api/reservations/'+ roomId + '/get');
            return response.data;
        } catch (error) {
            console.log(error);
        }
    }

    async addReservationToRoom(roomId, reservation){
        try {
            const response = await axios.post('http://localhost:8080/api/reservations/' + roomId + '/add-reservation', reservation);
            return response.data;
        } catch (error) {
            throw error
        }
    }

    async deleteReservation(reservationId){
        try {
            const response = await axios.delete('http://localhost:8080/api/reservations/delete/' + reservationId);
            return response.data;
        } catch (error) {
            console.log(error)
            throw error
        }
    }

}
export default new ReservationService