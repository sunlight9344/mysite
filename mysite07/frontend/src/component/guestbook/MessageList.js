import React, { useRef, useState } from 'react';
import Message from './Message';
import Modal from "react-modal";

import styles from '../../assets/scss/component/guestbook/MessageList.scss';
import modalStyles from '../../assets/scss/component/modal/modal.scss'; 

function MessageList({messages}) {

    const [modalData, setModalData] = useState({isOpen:false, password:''});
    const refForm = useRef(null);

    const openModal = (e) => {
        e.preventDefault();
        setModalData({isOpen:true});
    };

    const deleteMessage = async (e) => {
        e.preventDefault();
        console.log(e.target.password.value);
        setModalData({isOpen:true});
        
        // console.log(messageNo);
        // try{
        //     const response = await fetch(`/api/guestbook/${messageNo}`, {
        //         method: 'delete',
        //         headers: {
        //             'Content-Type': 'application/json',
        //             'Accept': 'application/json'
        //         },
        //         body: null
        //     });

        //     if(!response.ok) {
        //         throw new Error(`${response.status} ${response.statusText}`)
        //     }

        //     const json = await response.json();

        //     if(json.result !== 'success') {
        //         throw new Error(`${json.result} ${json.message}`)
        //     }

        //     // setTasks(tasks.filter(task => task.no !== taskNo));

        // } catch(err) {
        //     console.log(err);
        // }
    }

    return (
        <ul className={styles.MessageList}>
            {
                messages.map(e => <Message key={e.no} no={e.no} name={e.name} regDate={e.regDate} contents={e.contents} openModal={openModal} />)
            }
            <Modal
                isOpen={modalData.isOpen}
                onRequestClose={() => {setModalData({isOpen:false, password:''})}}
                shouldCloseOnOverlayClick={true}
                className={modalStyles.Modal}
                overlayClassName={modalStyles.Overlay}
                style={{content: {width:200}}}
                >
                <h1>비밀번호입력</h1>
                <div>
                    <form
                        onSubmit={deleteMessage}
                        className={styles.DeleteForm}
                        ref={refForm}>
                        <label>{modalData.isOpen || ''}</label>
                        <input
                            type={'password'}
                            autoComplete={'off'}
                            name={'password'}
                            placeholder={'비밀번호'}
                            value={modalData.password}
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