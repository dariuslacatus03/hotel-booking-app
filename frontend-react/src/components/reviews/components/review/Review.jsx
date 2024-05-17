export default function Review({review}){
    return (
        <div>
            <p>
                <b>Review</b> <br />
                {review.reviewText} <br />
                Rating: {review.rating}/5
            </p>
        </div>
    )
}