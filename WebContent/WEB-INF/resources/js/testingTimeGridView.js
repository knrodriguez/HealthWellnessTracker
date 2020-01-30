/**
 * 
 */


import {Calendar} from './fullcalendar/core';
import timeGridPlugin from './fullcalendar/timegrid';

let calendar = new Calendar(calendarEl, {
	plugins: [timeGridPlugin],
	defaultView: 'timeGridWeek'
});