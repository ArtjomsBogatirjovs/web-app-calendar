import {useForm} from 'react-hook-form'
import React from 'react'
import {dataArr} from './App'

function parseData(data) {
    let title = data.title
    let mail = data.email
    dataArr.add(title)
    dataArr.add(mail)
    console.log(dataArr)
    //here going to post data to backend
    return dataArr
}



const wait = function(duration = 1000) {
    return new Promise((resolve) => {
        window.setTimeout(resolve, duration)
    })
}



export function Form() {
    const {register, handleSubmit, formState, formState: {errors}} = useForm()
    const {isSubmitting, isSubmitSuccessful} = formState

    const onSubmit = async (data) => {
        await wait(2000)
        console.log(data)
        parseData(data)
    }

    return (
        <form className='Container-InputTitle' onSubmit={handleSubmit(onSubmit)} style={{float: 'left', position: 'relative', top: '10px' }}> 

        <div className='InputTitle' style={{marginTop: 10}} >
            <label htmlFor='titleEvent'>Title: </label>
            <input type="text" className='form-control' id="title"
                name="title" {...register('title', {required: 'You should enter a title'})}/>

            {errors.title && <span style={{color: '#d4d93a', marginLeft: 5}}>{errors.title.message}</span>}
        </div>

        <div className='InputEmail' style={{ marginTop: 10}}>
            <label htmlFor='email'>Email: </label>
            <input type="text" className='form-control' id="email"
                name="email" {...register('email', {required: 'You should enter an email'})}/>

            {errors.email && <span style={{color: '#d4d93a', marginLeft: 5}}>{errors.email.message}</span>}
        </div>

        <button disabled={isSubmitting} className='btn-send' style={{float: 'left', marginTop: 10, marginLeft: 5, backgroundColor: '#006edc', color: 'white'}}>Send</button>
      
      {isSubmitSuccessful && <div className='SubmitSuccess' style={{float: 'right', marginTop: 10, marginLeft: 5, color: '#00f700'}}>Your appointment is confirmed !</div>}

      </form>
    )
  }
