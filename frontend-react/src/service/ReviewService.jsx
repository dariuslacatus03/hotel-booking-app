import axios from "axios";

class ReviewService{

    async getReviewOfHotel(hotelId){
        try{
            const response = await axios.get('http://localhost:8080/api/reviews/' + hotelId + '/get');
            return response.data;
        } catch (error) {
            console.log(error);
            throw error;
        }
    }

    async addReviewToHotel(hotelId, review){
        try {
            const response = await axios.post('http://localhost:8080/api/reviews/' + hotelId +'/add-review', review);
            return response.data;
        } catch (error) {
            throw error
        }
    }

}
export default new ReviewService