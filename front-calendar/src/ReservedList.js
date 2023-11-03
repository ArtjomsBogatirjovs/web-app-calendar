import React, {useState, useEffect} from 'react';
import {deleteReservation, url} from './Request.js'

import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'

const MySwal = withReactContent(Swal)

const EntityList = () => {
    const [entities, setEntities] = useState([]);

    useEffect( () => {


        const fetchData = async () => {
            const response = await fetch( url + "reservations/get")
            const responseJson = await response.json()

            setEntities(responseJson)
        }

        fetchData()

    }, []);

    function onDeleteClick(index) {
        MySwal.fire({
            title: 'Are you sure',
            text: 'To delete this reservation?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete!',
            cancelButtonText: 'Cancel',
        }).then(async (result) => {
            if (result.isConfirmed) {

                const updatedEntities = [...entities];
                updatedEntities.splice(index, 1);

                try {
                    await deleteReservation(entities.at(index));
                } catch (error) {
                    console.error('Reservation error: ', error);
                }
            }
        });
    }

    return (
        <div className="reserved-list-container">
            <h2>Reserved time</h2>
            <table>
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Player 1</th>
                    <th>Player 2</th>
                    <th>Time</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                {entities.map((entity, index) => (
                    <tr key={index}>
                        <td>{entity.date}</td>
                        <td>{entity.playerOne}</td>
                        <td>{entity.playerTwo}</td>
                        <td>{`${entity.start} - ${entity.end}`}</td>
                        <td>
                            <button onClick={() => onDeleteClick(index)}>
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default EntityList;