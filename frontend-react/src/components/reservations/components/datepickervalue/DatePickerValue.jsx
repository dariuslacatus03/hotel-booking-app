import dayjs from 'dayjs';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { useState } from 'react';

export default function DatePickerValue({checkIn, setCheckIn, checkOut, setCheckOut}) {
  

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <DemoContainer sx={{flexDirection:"row", justifyContent:"center"}} components={['DatePicker', 'DatePicker']}>
        <DatePicker
          label="Check in"
          value={checkIn}
          onChange={(newDate) => setCheckIn(newDate.hour(10).minute(0).second(0))}
        />
        <DatePicker
          label="Check out"
          value={checkOut}
          onChange={(newDate) => setCheckOut(newDate.hour(10).minute(0).second(0))}
        />
      </DemoContainer>
    </LocalizationProvider>
  );
}