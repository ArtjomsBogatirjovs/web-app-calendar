import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'

const MySwal = withReactContent(Swal)


let url = "http://192.168.100.241:8090/calendar/"


const getAllAvailabilities = async function () {
    try {
        let response = await fetch(url + 'availabilities/get') //fetch renvoie une promesse Response
        if (response.ok) { //etre sur qu'il a recuperer des donnee
            let data = await response.json() // .json prend la response et renvoie une autre promesse qui contient les data en JSON
            console.log(data)
        } else {
            console.error('server return:', response.status)
        }
    } catch (e) {
        console.log(e)
    }
}

const getAvailabilitiesOfDay = async function (data) { //data = "2022-08-02"

    try {
        let response = await fetch(url + "availabilities/get/day/" + data)
        if (response.ok) {
            let data = await response.json()
            console.log(data)
        } else {
            console.error('server return: ', response.status)

        }
    } catch (e) {
        console.log(e)
    }
}


const getAllReservations = async function () {
    try {
        let response = await fetch(url + 'reservations/get')
        if (response.ok) {
            let data = await response.json()
            console.log(data)
        } else {
            console.error('server return: ', response.status)
        }
    } catch (e) {
        console.log(e)
    }
}

// Si reservation est supprime faut recreer availability
const postAvailability = async function (data) { //data = ReservationModel

    let response = await fetch(url + 'availabilities/post', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })

    if (response.ok) {
        let responseData = await response.json()
        console.log(responseData)
    } else {
        console.error('server return: ', response.status)
    }

}

const postReservation = async function (data) { //data = ReservationModel

    let response = await fetch(url + 'reservations/post', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })

    if (response.ok) {
        await MySwal.fire({
            icon:"success",
            title: "Time reserved!"
        })
    } else {
        response.text().then(text => {
            MySwal.fire({
                icon:"error",
                title: text
            })
        })
    }

}


const deleteReservation = async function (data) { //data = ReservationModel

    let response = await fetch(url + 'reservations/delete', {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(data)
    })

    if (response.ok) {
        let responseData = await response.json()
        console.log(responseData)
    } else {
        console.error('server return: ', response.status)
    }

}


const deleteAvailability = async function (id) { // id = id of the availability
    let response = await fetch(url + 'availabilities/delete/' + id, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        }
    })
    if (response.ok) {
        return true
    } else {
        return false
    }
}


export {
    getAllAvailabilities,
    getAllReservations,
    getAvailabilitiesOfDay,
    postAvailability,
    postReservation,
    deleteAvailability,
    deleteReservation
}

//  getAllReservations()

// getAvailabilitiesOfDay("2022-08-02")

// postReservation({
//     "date": "2022-08-03",
//     "start": "12:45",
//     "end": "13:00",
//     "title": "Meetings",
//     "email": "johny.smith@gmail.com"
// })


// deleteReservation({
//   "id": 2,
//   "date": "2022-08-03",
//   "start": "11:30",
//   "end": "11:45",
//   "title": "Meeting",
//   "email": "jane.snow@gmail.com"
// })

// deleteAvailability(1)