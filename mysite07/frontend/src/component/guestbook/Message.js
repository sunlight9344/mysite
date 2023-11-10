import React from 'react';
import styles from '../../assets/scss/component/guestbook/Message.scss';


function Message({no, name, regDate, contents, deleteMessage}) {

    return (
        <div className={styles.Message}>
            <strong>{name}</strong>
            <p style={{ display: 'flex', justifyContent: 'space-between' }}>
                <span>{contents}</span>
                <span>{regDate}</span>
            </p>
            <a href='#' onClick={() => {deleteMessage()}}/>
        </div>
    );
}

export default Message;