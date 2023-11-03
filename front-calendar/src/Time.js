import './Calendar.css';
import Times from './Times.js'

import React from 'react'
function Time(props) {

 return (
 <div className='.app > div:nth-child(4)'>
  {props.showTime ? <Times date={props.date} datesform={props.datesform}/> : null}
 </div>
  )
}

export default Time;