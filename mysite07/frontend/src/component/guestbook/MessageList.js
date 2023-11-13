import React, { useRef, useState } from 'react';
import Message from './Message';
import Modal from "react-modal";

import styles from '../../assets/scss/component/guestbook/MessageList.scss';
import modalStyles from '../../assets/scss/component/modal/modal.scss'; 

function MessageList({messages, setMessages}) {

    const [modalData, setModalData] = useState({isOpen:false, password:''});
    const [messageNo, setMessageNo] = useState(null);
    const refForm = useRef(null);
    const [alert, setAlert] = useState('');

    const openModal = (e, no) => {
        e.preventDefault();
        setModalData({isOpen:true});
        setMessageNo(no);
    };

    const deleteMessage = async (e) => {
        e.preventDefault();
        try{
            const response = await fetch(`/api/guestbook/${messageNo}?password=${e.target.password.value}`, {
                method: 'delete',
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
            json.data ? 
                (
                    setMessages(messages.filter(message => message.no !== messageNo)),
                    setModalData({isOpen:false}),
                    setAlert('')
                ):  setAlert('비번틀림')

        } catch(err) {
            console.log(err);
        }
    }

    return (
        <ul className={styles.MessageList}>
            {
                messages
                    .map(e => <Message key={e.no} no={e.no} name={e.name} regDate={e.regDate} contents={e.contents} openModal={openModal} />)
            }
            <Modal
                isOpen={modalData.isOpen}
                onRequestClose={() => {setModalData({isOpen:false, password:''}), setAlert('')}}
                shouldCloseOnOverlayClick={true}
                className={modalStyles.Modal}
                overlayClassName={modalStyles.Overlay}
                style={{content: {width:200}}}
                >
                <h1>비밀번호입력</h1>
                <h4>{alert}</h4>
                <div>
                    <form
                        onSubmit={(e)=>deleteMessage(e)}
                        className={styles.DeleteForm}
                        ref={refForm}>
                        <label>{modalData.isOpen || ''}</label>
                        <input
                            type={'password'}
                            autoComplete={'off'}
                            name={'password'}
                            placeholder={'비밀번호'}
                            defaultValue={modalData.password || ''}
                        />
                        <div className={modalStyles['modal-dialog-buttons']}>
                            <button type='submit'>
                                확인
                            </button>
                            <button
                                type='button'
                                onClick={()=>{setModalData({isOpen:false})}}>
                                취소
                            </button>
                        </div>
                    </form>
                </div>
            </Modal>
        </ul>
    );
}

export default MessageList;