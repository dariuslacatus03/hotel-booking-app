export default function Review({review}){
    return (
        <div>
            <p>
                Review {review.id}: <br />
                {review.reviewText} <br />
                Rating: {review.rating}/5
            </p>
        </div>
    )
}