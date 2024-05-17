import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Slider from '@mui/material/Slider';
import MuiInput from '@mui/material/Input';
import { useState } from 'react';

const Input = styled(MuiInput)`
  width: 80px;
`;

export default function LongitudeSlider({longitude, setLongitude}) {

  const handleSliderChange = (event, newLongitude) => {
    setLongitude(newLongitude);
  };

  const handleInputChange = (event) => {
    const newLongitude = event.target.value === '' ? 0 : Number(event.target.value);
    setLongitude(newLongitude);
  };

  const handleBlur = () => {
    if (longitude < -180) {
      setLongitude(-180);
    } else if (longitude > 180) {
      setLongitude(180);
    }
  };

  return (
    <Box sx={{ width: 300 }}>
      <Typography id="input-slider" gutterBottom>
        Longitude
      </Typography>
      <Grid container spacing={2} alignItems="center">
        <Grid item xs>
          <Slider
            value={typeof longitude === 'number' ? longitude : 0}
            onChange={handleSliderChange}
            aria-labelledby="input-slider"
            min={-180}
            max={180}
            step={0.000001}
          />
        </Grid>
        <Grid item>
          <Input
            value={longitude}
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
