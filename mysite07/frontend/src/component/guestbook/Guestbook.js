import React, { useEffect, useState } from 'react';
import styles from '../../assets/scss/component/guestbook/Guestbook.scss';
import WriteForm from './WriteForm';
import MessageList from './MessageList';

function Guestbook() {

    const [messages, setMessages] = useState(null);

    const fetchMessages = async () => {
        try{
            const response = await fetch(`/api/guestbook`, {
                method: 'get',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: null
            });

            if(!response.ok) {
                throw new Error(`${response.status} ${response.statusText}`)
            }

            const json = await response.json();

            if(json.result !== 'success') {
                throw new Error(`${json.result} ${json.message}`)
            }

            setMessages(json.data);

        } catch(err) {
            console.log(err);
        }
    }

    const addMessage = async (message) => {
        try{
            const response = await fetch('/api/guestbook', {
                method: 'post',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify(message)
            });

            if(!response.ok) {
                throw new Error(`${response.status} ${response.statusText}`)
            }

            const json = await response.json();

            if(json.result !== 'success') {
                throw new Error(`${json.result} ${json.message}`)
            }

            setMessages([json.data, ...messages]);
            
        } catch(err) {
            console.log(err);
        }
    }

    useEffect(() => {
        fetchMessages();
    }, [])

    return (
        <div className={styles.Guestbook}>
            <h2>방명록이요</h2>
            <WriteForm addMessage={addMessage} />
            { messages ? <MessageList messages={messages} setMessages={setMessages}/> : null }
        </div>
    );
}

export default Guestbook;