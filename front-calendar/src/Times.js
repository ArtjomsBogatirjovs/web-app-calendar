import React, {useEffect} from 'react'
import {useState} from 'react';
import './Calendar.css';
import {Form} from './Form';
import {useForm} from "react-hook-form";
import {dataArr, formatDate} from './App';
import {postReservation} from "./Request";

function Times(props) {

    const [event, setEvent] = useState(null)
    const [info, setInfo] = useState(false)
    const [availabilities, setAvailabilities] = useState([])
    const [firstPlayerName, setFirstPlayerName] = useState('');
    const [secondPlayerName, setSecondPlayerName] = useState('');
    const [selectedTime, setSelectedTime] = useState('');

    const handleConfirm = async () => {
        let time = selectedTime.split(' to ')
        const data = {
            playerOne: firstPlayerName,
            playerTwo: secondPlayerName,
            date: formatDate(props.date),
            start: time[0],
            end: time[1]
        };
        try {
            await postReservation(data);
        } catch (error) {
            console.error('Reservation error: ', error);
        }
    };

    function displayInfo(e) {
        setInfo(true);
        setEvent(e.target.innerText);
        const dates = e.target.innerText
        parseDate(dates)
    }

    function parseDate(dates) {
        const creneau = dates.split(' ')
        const start = creneau[0]
        const end = creneau[2]
        dataArr.add(start)
        dataArr.add(end)
    }


    useEffect(() => {

        let day = formatDate(props.date)
        const fetchData = async (day) => {
            const response = await fetch("http://192.168.100.241:8090/calendar/availabilities/get/day/" + day)
            const responseJson = await response.json()

            setAvailabilities(responseJson)
            console.log(responseJson[0])
            setSelectedTime(responseJson[0].start + " to " + responseJson[0].end)
        }

        fetchData(day)

    }, [])


    //console.log(availabilities)

    const {register, handleSubmit} = useForm();
    const onSubmit = data => console.log(data + 'dsadasdas');

    return (

        <div className='times'>
            <div className='ButtonTitle'>
                <div className='Bouton'>
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <label htmlFor='timeSlot'>Select a time slot: </label>
                        <select {...register("timeSlot")}
                                onChange={(e) => setSelectedTime(e.target.value)}>
                            {availabilities.map((currTime) => {

                                return <option key={currTime.start}
                                               value={currTime.start + " to " + currTime.end}
                                               onClick={(e) => displayInfo(e)}
                                >
                                    {currTime.start} to {currTime.end}
                                </option>
                            })}
                        </select>

                    </form>
                </div>
                {info ? <Form/> : null}

            </div>


            <p style={{float: 'left', marginRight: 15}}>
                {info ? `Your appointment is set from: ${event} ${props.date.toDateString()}` : null}
            </p>
            <div>
                <input
                    type="text"
                    placeholder="First player name"
                    value={firstPlayerName}
                    onChange={(e) => setFirstPlayerName(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Second palyer name"
                    value={secondPlayerName}
                    onChange={(e) => setSecondPlayerName(e.target.value)}
                />
                <button onClick={handleConfirm}>Confirm</button>
            </div>
        </div>
    )
}

export default Times;