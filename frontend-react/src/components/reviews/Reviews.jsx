import Review from "./components/review/Review";

export default function Reviews({reviewsOfHotel}){
    return (
        <div className="review-list-layout">
            <ul style={{listStyle:"none"}}>
                {reviewsOfHotel.map(review => (
                                <li data-testid={"list-item"} key={review.id}>
                                        <Review review = {review}/>
                                </li>
                    ))}
            </ul>
        </div>
    )
}