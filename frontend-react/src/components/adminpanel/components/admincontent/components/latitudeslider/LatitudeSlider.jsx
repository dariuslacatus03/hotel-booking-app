import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Slider from '@mui/material/Slider';
import MuiInput from '@mui/material/Input';

const Input = styled(MuiInput)`
  width: 60px;
`;

export default function LatitudeSlider({latitude, setLatitude}) {

  const handleSliderChange = (event, newLatitude) => {
    setLatitude(newLatitude);
  };

  const handleInputChange = (event) => {
    const newLatitude = event.target.value === '' ? 0 : Number(event.target.value);
    setLatitude(newLatitude);
  };

  const handleBlur = () => {
    if (latitude < -90) {
      setLatitude(-90);
    } else if (latitude > 90) {
      setLatitude(90);
    }
  };

  return (
    <Box sx={{ width: 300 }}>
      <Typography id="input-slider" gutterBottom>
        Latitude
      </Typography>
      <Grid container spacing={2} alignItems="center">
        <Grid item xs>
          <Slider
            value={typeof latitude === 'number' ? latitude : 0}
            onChange={handleSliderChange}
            aria-labelledby="input-slider"
            min={-90}
            max={90}
            step={0.000001}
          />
        </Grid>
        <Grid item>
          <Input
            value={latitude}
            size="small"
            onChange={handleInputChange}
            onBlur={handleBlur}
            inputProps={{
              step: 0.000001,
              min: -90,
              max: 90,
              type: 'number',
              'aria-labelledby': 'input-slider',
            }}
          />
        </Grid>
      </Grid>
    </Box>
  );
}
