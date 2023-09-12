import React from 'react';
import './AppointmentTable.css';

const AppointmentTable = ({ appointments }) => {
  return (
    <div className="appointment-table">
      {Object.keys(appointments).map((date, index) => (
        <div key={index} className="date-slot">
          <h3 className='date-heading'>{date}</h3>
          <table className='time-slot-table'>
            <thead>
              <tr>
                <th>Time Slot</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {appointments[date].map((slot, slotIndex) => (
                <tr key={slotIndex}>
                  <td>{slot.time}</td>
                  <td>{slot.available ? 'Available' : 'Not Available'}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ))}
    </div>

    
  );
};

export default AppointmentTable;
