import React, {useState, useEffect} from 'react';

const EntityList = () => {
    const [entities, setEntities] = useState([]);

    useEffect( () => {


        const fetchData = async () => {
            const response = await fetch("http://192.168.100.241:8090/calendar/reservations/get")
            const responseJson = await response.json()

            setEntities(responseJson)
        }

        fetchData()

    }, []);

    return (
        <div className="reserved-list-container">
            <h2>Reserved time</h2>
            <ul>
                {entities.map((entity, index) => (
                    <li key={index}>
                        {entity.date} {entity.playerOne} vs. {entity.playerTwo}  from {entity.start} to {entity.end}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default EntityList;