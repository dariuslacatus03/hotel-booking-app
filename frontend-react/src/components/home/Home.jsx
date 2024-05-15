import Hotels from "../hotels/Hotels";

export default function Home({ userLocation, hotelList }) {
    
    return (
        <div style={{display: 'flex', width: '100%'}}>
            <Hotels hotelList = {hotelList}/>
        </div>
    );
  }
  