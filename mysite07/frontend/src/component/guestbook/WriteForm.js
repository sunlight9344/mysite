import React, { useRef } from 'react';
import styles from '../../assets/scss/component/guestbook/WriteForm.scss';

function WriteForm({addMessage}) {

    const refForm = useRef(null);

    return (
        <div>
            <form
                ref={refForm}
                className={styles.WriteForm}
                onSubmit={(e) => {
                    e.preventDefault();
                    const message = {
                        name: e.target.name.value,
                        password: e.target.password.value,
                        contents: e.target.message.value,
                        regDate: '지금'
                    }
                    addMessage(message);
                    refForm.current.reset();
                }}
                >
            <input
                type={'text'}
                name={'name'}
                placeholder={'이름'}
                autoComplete={' off'}/>
            <input
                type={'password'}
                name={'password'}
                placeholder={'비밀번호'}
                autoComplete={'off'}/>
            <textarea
                name={'message'}
                placeholder={'메세지(내용)'}/>
            <input
                type={'submit'}
                value={'보내기'}
                autoComplete={'off'}
                />
            </form>
        </div>
    );
}

export default WriteForm;